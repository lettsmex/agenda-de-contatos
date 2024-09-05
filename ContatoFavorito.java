class ContatoFavorito extends Contato {
    public ContatoFavorito(String nome, long numero, String email, String endereco, String aniversario) {
        super(nome, numero, email, endereco, aniversario);
    }

    public String toString() {
        return super.toString() + "\n*Contato Favorito*";
    }
}
