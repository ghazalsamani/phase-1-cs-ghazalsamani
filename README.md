# Simple Compiler Phase1

**Build & Run**:

```bash
antlr4 -visitor -package parser -o src/parser src/antlr/SimpleLang.g4
javac -cp .:antlr-4.9.2-complete.jar src/**/*.java
java -cp .:antlr-4.9.2-complete.jar main.Main tests/sample1.c
```