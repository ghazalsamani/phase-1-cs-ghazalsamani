package main;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import parser.SimpleLangLexer;
import parser.SimpleLangParser;
import ast.*;
import visitor.*;

public class Main {
    public static void main(String[] args) throws Exception {
        CharStream in = CharStreams.fromFileName(args[0]);
        SimpleLangLexer lex = new SimpleLangLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lex);
        SimpleLangParser p = new SimpleLangParser(tokens);
        ParseTree tree = p.program();
        ASTBuilder builder = new ASTBuilder();
        ProgramNode prog = (ProgramNode) builder.visit(tree);
        EvalVisitor eval = new EvalVisitor();
        eval.visit(prog);
    }
}
