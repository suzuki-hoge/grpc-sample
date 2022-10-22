# gRPC Sample
## Requirements
- java

## Setup
### Server
```
$ ./gradlew generateProto
```

### Client
```
$ ./gradlew generateProto
```

## Run
### Server
```
$ ./gradlew run
```

### Client
```
$ ./gradlew run
```

### Result ( excerpts from 2 terminals )
```
server: start
client: start
client: request  ( id = E292C512-03D7-4FA8-9D47-7513EDCC8008 )
server: received ( id = E292C512-03D7-4FA8-9D47-7513EDCC8008 )
client: received ( id = E292C512-03D7-4FA8-9D47-7513EDCC8008, title = Lorem ipsum dolor sit amet, limit = 2023-01-01T12:34:56 )
client: end
```

## Branches
- [master](https://github.com/suzuki-hoge/grpc-sample/tree/master)

