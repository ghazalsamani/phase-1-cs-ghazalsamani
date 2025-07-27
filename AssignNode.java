package ast;
public class AssignNode implements ASTNode {
    public final String name;
    public final ASTNode expr;
    public AssignNode(String name, ASTNode expr) { this.name = name; this.expr = expr; }
    public <T> T accept(ASTVisitor<T> v) { return v.visit(this); }
}
