package ast;
public class IntLiteralNode implements ASTNode {
    public final int value;
    public IntLiteralNode(int v) { this.value = v; }
    public <T> T accept(ASTVisitor<T> v) { return v.visit(this); }
}
