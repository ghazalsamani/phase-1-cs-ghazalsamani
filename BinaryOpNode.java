package ast;
public class BinaryOpNode implements ASTNode {
    public final String op;
    public final ASTNode left, right;
    public BinaryOpNode(ASTNode l, String op, ASTNode r) { this.left = l; this.op = op; this.right = r; }
    public <T> T accept(ASTVisitor<T> v) { return v.visit(this); }
}
