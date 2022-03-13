package info.jakepark.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.util.UriBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.function.Function;

@AutoConfigureWebTestClient
public abstract class WebTestClientUtils {
  @Autowired protected WebTestClient webTestClient;

  public RequestUriSpec get() {
    return new RequestUriSpec(HttpMethod.GET);
  }

  public RequestUriSpec post() {
    return new RequestUriSpec(HttpMethod.POST);
  }

  public RequestUriSpec delete() {
    return new RequestUriSpec(HttpMethod.DELETE);
  }

  public RequestUriSpec put() {
    return new RequestUriSpec(HttpMethod.PUT);
  }

  public class RequestUriSpec {

    @NotNull private final HttpMethod httpMethod;
    private MultiValueMap<String, String> headers = null;
    private Function<UriBuilder, URI> uriFunction = null;

    private Object body = null;
    private BodyInserter bodyInserter;

    private String uri = null;

    private Object[] uriVariables;

    RequestUriSpec(HttpMethod httpMethod) {
      this.httpMethod = httpMethod;
    }

    public RequestUriSpec uri(Function<UriBuilder, URI> uriFunction) {
      this.uriFunction = uriFunction;
      return this;
    }

    public RequestUriSpec uri(String uri, Object... uriVariables) {
      this.uri = uri;
      this.uriVariables = uriVariables;
      return this;
    }

    public RequestUriSpec bodyValue(Object body) {
      this.body = body;
      return this;
    }

    public RequestUriSpec body(BodyInserter bodyInserter) {
      this.bodyInserter = bodyInserter;
      return this;
    }

    public RequestUriSpec headersValue(MultiValueMap<String, String> headers) {
      this.headers = headers;
      return this;
    }

    public <T> T getResponseBody(ParameterizedTypeReference<T> bodyType) {
      return this.getMethod()
          .exchange()
          .expectStatus()
          .isOk()
          .expectBody(bodyType)
          .returnResult()
          .getResponseBody();
    }

    public WebTestClient.ResponseSpec getResponseOk() {
      return this.getMethod().exchange().expectStatus().isOk();
    }

    public WebTestClient.ResponseSpec getResponseUnAuthorized() {
      return this.getMethod().exchange().expectStatus().isUnauthorized();
    }

    public WebTestClient.ResponseSpec getResponse5xxServerError() {
      return this.getMethod().exchange().expectStatus().is5xxServerError();
    }

    public WebTestClient.ResponseSpec getResponseBadRequest() {
      return this.getMethod().exchange().expectStatus().isBadRequest();
    }

    public WebTestClient.ResponseSpec getResponseForbidden() {
      return this.getMethod().exchange().expectStatus().isForbidden();
    }

    private WebTestClient.RequestBodyUriSpec getMethod() {
      WebTestClient.RequestBodyUriSpec method = webTestClient.method(this.httpMethod);
      if (StringUtils.hasText(this.uri)) {
        if (this.uriVariables.length > 0)
          method.uri(this.uri, this.uriVariables).accept(MediaType.APPLICATION_JSON);
        else method.uri(this.uri).accept(MediaType.APPLICATION_JSON);
      } else {
        method.uri(this.uriFunction).accept(MediaType.APPLICATION_JSON);
      }

      if (this.body != null) {
        method.bodyValue(this.body);
      } else if (!ObjectUtils.isEmpty(this.bodyInserter)) {
        method.body(this.bodyInserter);
        method.contentType(MediaType.APPLICATION_JSON);
      }

      if (this.headers != null) method.headers(h -> h.addAll(this.headers));

      return method;
    }
  }
}
