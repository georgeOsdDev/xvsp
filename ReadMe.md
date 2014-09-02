# Super simple benchmarking

### For PlayFramework application

    cd playframework

Package application

    activator clean stage

Run application
(start playframework with java option as same as xitrum)

    ./target/universal/stage/bin/myapp \
    -Dapplication.secret=abcdefghijk \
    -J-Xmx1024m \
    -J-Xms256m \
    -J-XX:MaxPermSize=128m \
    -J-XX:+HeapDumpOnOutOfMemoryError \
    -J-XX:+AggressiveOpts \
    -J-XX:+OptimizeStringConcat \
    -J-XX:+UseFastAccessorMethods \
    -J-XX:+UseParNewGC \
    -J-XX:+UseConcMarkSweepGC \
    -J-XX:+CMSParallelRemarkEnabled \
    -J-XX:+CMSClassUnloadingEnabled \
    -J-XX:SurvivorRatio=8 \
    -J-XX:MaxTenuringThreshold=1 \
    -J-XX:CMSInitiatingOccupancyFraction=75 \
    -J-XX:+UseCMSInitiatingOccupancyOnly \
    -Djava.awt.headless=true

Application will run at http://localhost:9000

### For Xitrum application

    cd xitrum

Package application

    ./sbt/sbt xitrum-package

Run application

    ./target/xtrum/runner quickstart.Boot

Application will run at http://localhost:8000

---

### Benchmark

iMac
* 21.5-inch, Late 2012
* Processor  2.7 GHz Intel Core i5
* Memory  8 GB 1600 MHz DDR3
* Graphics  NVIDIA GeForce GT 640M 512 MB
* Serial Number  C02KD419DNCR
* Software  OS X 10.9.4 (13E28)


    sw_vers
    ProductName:	Mac OS X
    ProductVersion:	10.9.4
    BuildVersion:	13E28

    httperf --version
    httperf: httperf-0.9.0 compiled Nov 18 2012 without DEBUG without TIME_SYSCALLS.


#### PlayFramework

```
httperf --client=0/1 --server=localhost --port=8000 --uri=/ --send-buffer=4096 --recv-buffer=16384 --num-conns=1000 --num-calls=10 --rate 100 --timeout 5
```

    httperf --timeout=5 --client=0/1 --server=localhost --port=8000 --uri=/ --rate=100 --send-buffer=4096 --recv-buffer=16384 --num-conns=1000 --num-calls=10
    httperf: warning: open file limit > FD_SETSIZE; limiting max. # of open files to FD_SETSIZE
    Maximum connect burst length: 1

    Total: connections 1000 requests 10000 replies 10000 test-duration 9.993 s

    Connection rate: 100.1 conn/s (10.0 ms/conn, <=1 concurrent connections)
    Connection time [ms]: min 2.5 avg 2.7 max 5.8 median 2.5 stddev 0.3
    Connection time [ms]: connect 0.1
    Connection length [replies/conn]: 10.000

    Request rate: 1000.7 req/s (1.0 ms/req)
    Request size [B]: 62.0

    Reply rate [replies/s]: min 999.9 avg 999.9 max 999.9 stddev 0.0 (1 samples)
    Reply time [ms]: response 0.3 transfer 0.0
    Reply size [B]: header 136.0 content 406.0 footer 0.0 (total 542.0)
    Reply status: 1xx=0 2xx=10000 3xx=0 4xx=0 5xx=0

    CPU time [s]: user 2.47 system 7.51 (user 24.7% system 75.2% total 99.9%)
    Net I/O: 590.3 KB/s (4.8*10^6 bps)

    Errors: total 0 client-timo 0 socket-timo 0 connrefused 0 connreset 0
    Errors: fd-unavail 0 addrunavail 0 ftab-full 0 other 0

#### Xitrum

```
httperf --client=0/1 --server=localhost --port=8000 --uri=/ --send-buffer=4096 --recv-buffer=16384 --num-conns=1000 --num-calls=10 --rate 100 --timeout 5
```

    httperf --timeout=5 --client=0/1 --server=localhost --port=9000 --uri=/ --rate=100 --send-buffer=4096 --recv-buffer=16384 --num-conns=1000 --num-calls=10
    httperf: warning: open file limit > FD_SETSIZE; limiting max. # of open files to FD_SETSIZE
    Maximum connect burst length: 3

    Total: connections 1000 requests 10000 replies 10000 test-duration 9.992 s

    Connection rate: 100.1 conn/s (10.0 ms/conn, <=4 concurrent connections)
    Connection time [ms]: min 1.3 avg 1.6 max 38.9 median 1.5 stddev 1.2
    Connection time [ms]: connect 0.1
    Connection length [replies/conn]: 10.000

    Request rate: 1000.8 req/s (1.0 ms/req)
    Request size [B]: 62.0

    Reply rate [replies/s]: min 1000.1 avg 1000.1 max 1000.1 stddev 0.0 (1 samples)
    Reply time [ms]: response 0.2 transfer 0.0
    Reply size [B]: header 80.0 content 403.0 footer 0.0 (total 483.0)
    Reply status: 1xx=0 2xx=10000 3xx=0 4xx=0 5xx=0

    CPU time [s]: user 2.55 system 7.40 (user 25.5% system 74.0% total 99.5%)
    Net I/O: 532.7 KB/s (4.4*10^6 bps)

    Errors: total 0 client-timo 0 socket-timo 0 connrefused 0 connreset 0
    Errors: fd-unavail 0 addrunavail 0 ftab-full 0 other 0
