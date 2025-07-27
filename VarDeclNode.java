package ast;
public class VarDeclNode implements ASTNode {
    public final String name;
    public VarDeclNode(String name) { this.name = name; }
    public <T> T accept(ASTVisitor<T> v) { return v.visit(this); }
}
