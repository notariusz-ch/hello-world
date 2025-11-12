package ch.notariusz.helloServer;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

public class HelloServerApplication {

	public static void main(String[] args) {
        RouterFunction<?> route = route(GET("/"),
                request -> ServerResponse.ok().body(fromValue("Hello test23.")));

        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
        ReactorHttpHandlerAdapter reactorHttpHandlerAdapter = new ReactorHttpHandlerAdapter(httpHandler);

        DisposableServer server =
                 HttpServer.create()
                         .host( "0.0.0.0" )
                         .port( 8080 )
                         .handle( reactorHttpHandlerAdapter )
                         .bindNow();

        server.onDispose().block();
	}

}
