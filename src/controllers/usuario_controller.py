from models.usuario import Usuario

class UsuarioController:
    def __init__(self):
        self.usuario: Usuario | None = None

    def cadastrar(self, nome, email, telefone, cidade):
        self.usuario = Usuario(nome, email, telefone, cidade)

    def logado(self) -> bool:
        return self.usuario is not None

    def confirmar_participacao(self, nome_evento: str):
        self.usuario.confirmar_evento(nome_evento)

    def cancelar_participacao(self, nome_evento: str):
        self.usuario.cancelar_evento(nome_evento)

    def meus_eventos(self) -> list[str]:
        return self.usuario.eventos_confirmados