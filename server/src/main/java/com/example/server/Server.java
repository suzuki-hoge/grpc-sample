package com.example.server;

import com.example.todo.TodoRequest;
import com.example.todo.TodoResponse;
import com.example.todo.TodoServiceGrpc;
import com.google.protobuf.Timestamp;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("server: start");

        io.grpc.Server server = ServerBuilder.forPort(4000)
                .addService(new TodoImpl())
                .build();

        server.start();

        server.awaitTermination();
    }

    static class TodoImpl extends TodoServiceGrpc.TodoServiceImplBase {
        @Override
        public void find(TodoRequest request, StreamObserver<TodoResponse> responseObserver) {
            System.out.printf("server: received ( id = %s )\n", request.getId());

            TodoResponse response = TodoResponse.newBuilder()
                    .setId(request.getId())
                    .setTitle("Lorem ipsum dolor sit amet")
                    .setLimit(parse(LocalDateTime.of(2023, 1, 1, 12, 34, 56)))
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    private static Timestamp.Builder parse(LocalDateTime value) {
        return Timestamp.newBuilder()
                .setSeconds(value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
