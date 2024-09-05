public class Contato {
     private String nome;
     private long numero;
     private String email;
     private String endereco;
     private String aniversario;

    public Contato(String nome, long numero, String email, String endereco, String aniversario) {
        this.nome = nome;
        this.numero = numero;
        this.email = email;
        this.endereco = endereco;
        this.aniversario = aniversario;
    }

    public String getNome() {
        return nome;
    }

    public long getNumero() {
        return numero;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() { return endereco; }

    public String getAniversario() {
        return aniversario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public String toString() {
        return "Nome: " + nome + "\nTelefone: " + numero + "\nE-mail: " + email +
                "\nEndereço: " + endereco + "\nAniversário: " + aniversario;
    }
}
