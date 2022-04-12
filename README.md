# Free Monad
### Motivated by the desire to understand Doobie

## Resources
- https://typelevel.org/cats/datatypes/freemonad.html
- [SBTB 2015: Rob Norris, Programs as Values: JDBC Programming with Doobie](https://www.youtube.com/watch?v=M5MF6M7FHPo&t=486s)
- [Daniel Spiewak's talk on Free Monad](https://www.youtube.com/watch?v=cxMo1RMsD0M&t=146s)


## To run the KVStore test:
```shell
./scripts/runKvStore.sh
```


## To run CountryData

### On terminal 1
```shell
./scripts/start-localDb.sh
#46cc1d90f2768384a218a63da82b48cb6434133c475e6eee3a52e3093714dd4d

./scripts/initDb.sh
: << 'END'
[Mon Apr 11 23:18:23 CDT 2022]
Entering: /Users/dj.chen/repoMy/freeKeyValueStore/scripts/initDb.sh
Work dir: /Users/dj.chen/repoMy/freeKeyValueStore
[Mon Apr 11 23:18:23 CDT 2022]
Work dir: /Users/dj.chen/repoMy/freeKeyValueStore
Exiting : /Users/dj.chen/repoMy/freeKeyValueStore/scripts/initDb.sh
ExitCode: 0```
END
```

### On terminal 2
```shell
./scripts/runCountryData.sh
```

### On terminal 1
```shell
./scripts/stop-localDb.sh
#test-db-pg
#test-db-pg
````
