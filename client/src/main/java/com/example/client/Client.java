package com.example.client;

import com.example.todo.TodoRequest;
import com.example.todo.TodoResponse;
import com.example.todo.TodoServiceGrpc;
import com.google.protobuf.Timestamp;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Client {
    public static void main(String[] args) {
        System.out.println("client: start");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 4000)
                .usePlaintext()
                .build();

        TodoServiceGrpc.TodoServiceBlockingStub stub = TodoServiceGrpc.newBlockingStub(channel);

        TodoRequest request = TodoRequest.newBuilder()
                .setId("E292C512-03D7-4FA8-9D47-7513EDCC8008")
                .build();

        System.out.printf("client: request  ( id = %s )\n", request.getId());

        TodoResponse response = stub.find(request);

        System.out.printf(
                "client: received ( id = %s, title = %s, limit = %s )\n",
                response.getId(), response.getTitle(), parse(response.getLimit())
        );

        System.out.println("client: end");
    }

    private static LocalDateTime parse(Timestamp value) {
        return Instant.ofEpochMilli(value.getSeconds()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
