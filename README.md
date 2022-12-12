# Redis

## Goal

1. [CrudRepository를 통한 객체 관리(Post 예제)](https://github.com/leeheefull/redis/tree/main/src/main/java/com/leeheeefull/redis/post)
2. [RedisTemplate을 통한 객체 관리(Log 예제)](https://github.com/leeheefull/redis/tree/main/src/main/java/com/leeheeefull/redis/log)

## Redis cli guide

### Install redis

> 레디스를 설치합니다.

```shell
brew install redis
```

### Start redis service

> 레디스 서비스를 시작합니다.

```shell
brew services start redis
```

### Start redis CLI

> 레디스를 CLI로 실행합니다.

```shell
redis-cli
```

### Stop redis service

> 레디스 서비스를 멈춥니다.

```shell
brew services stop redis
```

### Restart redis service

> 레디스 서비스를 재시작합니다.

```shell
brew services restart redis
```