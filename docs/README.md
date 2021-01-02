# simpledb
SimpleDB is developed by Edward Sciore.

http://www.cs.bc.edu/~sciore/simpledb

## Quick Start

### start the server

```bash
java simpledb/server/Startup .
```

### seed database

```bash
java studentClient.simpledb.CreateStudentDB
```

### query records

```bash
java studentClient.simpledb.FindMajors math
```

## Roadmap

- dive into the source code and complete missing unit tests
- enhance SimpleDB and provide more features
- re-write in Golang: https://github.com/chaoyangnz/breezedb