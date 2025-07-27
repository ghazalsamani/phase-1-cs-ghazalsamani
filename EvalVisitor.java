package visitor;
import ast.*;
import java.util.*;

public class EvalVisitor implements ASTVisitor<Integer> {
    private final Map<String,Integer> variables = new HashMap<>();
    private final Map<String,Integer> stmtCount = new LinkedHashMap<>();
    private int exprDepth = 0;
    private int maxDepth = 0;

    public Integer visit(ProgramNode node) {
        stmtCount.put("global", 0);
        for(var s: node.statements) s.accept(this);
        stmtCount.forEach((scope, cnt) -> System.out.println(scope + ": " + cnt));
        System.out.println("max_expr_depth: " + maxDepth);
        return 0;
    }

    public Integer visit(VarDeclNode node) {
        String scope = currentScope();
        stmtCount.put(scope, stmtCount.get(scope) + 1);
        variables.put(node.name, 0);
        return 0;
    }

    public Integer visit(AssignNode node) {
        String scope = currentScope();
        stmtCount.put(scope, stmtCount.get(scope) + 1);
        int val = node.expr.accept(this);
        variables.put(node.name, val);
        return val;
    }

    public Integer visit(IfNode node) {
        String scope = currentScope();
        stmtCount.put(scope, stmtCount.get(scope) + 1);
        int cond = node.condition.accept(this);
        if (cond != 0) {
            enterScope("if_block", node.thenBlock);
        } else if (node.elseBlock != null) {
            enterScope("else_block", node.elseBlock);
        }
        return 0;
    }

    public Integer visit(WhileNode node) {
        String scope = currentScope();
        stmtCount.put(scope, stmtCount.get(scope) + 1);
        while (node.condition.accept(this) != 0) {
            enterScope("while_block", node.body);
        }
        return 0;
    }
    public Integer visit(BlockNode node) { throw new UnsupportedOperationException(); }

    public Integer visit(BinaryOpNode node) {
        exprDepth++;
        maxDepth = Math.max(maxDepth, exprDepth);
        int l = node.left.accept(this);
        int r = node.right.accept(this);
        exprDepth--;
        switch (node.op) {
            case "+": return l + r;
            case "-": return l - r;
            case "*": return l * r;
            case "/": return l / r;
        }
        return 0;
    }

    public Integer visit(IntLiteralNode node) { return node.value; }
    public Integer visit(VarRefNode node) { return variables.getOrDefault(node.name, 0); }

    private void enterScope(String name, BlockNode block) {
        stmtCount.put(name, 0);
        for (var s : block.statements) s.accept(this);
    }

    private String currentScope() {
        List<String> keys = new ArrayList<>(stmtCount.keySet());
        return keys.get(keys.size() - 1);
    }
}
