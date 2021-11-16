package entidades.usuarios;

import utilitarios.Arquivo;
import utilitarios.HashExtensivel;

public class ArquivoUsuario extends Arquivo<Usuario> {

  String nomePasta;
  HashExtensivel<ParEmailId> indiceIndiretoEmail;

  public ArquivoUsuario(String nome) throws Exception {
    super(Usuario.class.getConstructor(), nome);
    this.nomePasta = nome;
    indiceIndiretoEmail = new HashExtensivel<>(ParEmailId.class.getConstructor(), 4, this.nomePasta + "/email1.db",
        this.nomePasta + "/email2.db");
  }

  // --------------------------------------------------------
  // CREATE
  // --------------------------------------------------------
  @Override
  public int create(Usuario objeto) throws Exception {
    int id = super.create(objeto);
    indiceIndiretoEmail.create(new ParEmailId(objeto.getEmail(), id));
    return id;
  }

  // --------------------------------------------------------
  // READ
  // --------------------------------------------------------
  // Usaremos o método read por ID da superclasse
  // Assim, não precisamos implementar algo como:
  // @Override
  // public Usuario read(int id) throws Exception {
  // return super.read(id);
  // }
  //

  // Método para leitura por título (específico para Livros)
  public Usuario read(String s) throws Exception {
    ParEmailId ptid = indiceIndiretoEmail.read(s.hashCode());
    if (ptid == null)
      return null;
    return read(ptid.getID());
  }

  // --------------------------------------------------------
  // UPDATE
  // --------------------------------------------------------
  // A atualização aqui ficou um pouco mais complicada, porque precisamos do
  // email antigo do usuairo para alterar o índice indireto. Assim, precisamos,
  // em primeiro lugar, recuperar o email antigo
  @Override
  public boolean update(Usuario objetoNovo) throws Exception {
    Usuario objetoAntigo = super.read(objetoNovo.getID());
    if (objetoAntigo != null && super.update(objetoNovo)) {
      indiceIndiretoEmail.delete(objetoAntigo.getEmail().hashCode());
      indiceIndiretoEmail.create(new ParEmailId(objetoNovo.getEmail(), objetoNovo.getID()));
      return true;
    }
    return false;
  }

  // --------------------------------------------------------
  // DELETE
  // --------------------------------------------------------
  @Override
  public boolean delete(int id) throws Exception {
    Usuario objeto = super.read(id);
    if (objeto != null && super.delete(id)) {
      indiceIndiretoEmail.delete(objeto.getEmail().hashCode());
      return true;
    }
    return false;
  }

  // --------------------------------------------------------
  // CLOSE
  // --------------------------------------------------------
  @Override
  public void close() throws Exception {
    super.close();
    indiceIndiretoEmail.close();
  }

}