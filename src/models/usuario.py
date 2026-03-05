class Usuario:
    def __init__(self, nome: str, email: str, telefone: str, cidade: str):
        self.nome = nome
        self.email = email
        self.telefone = telefone
        self.cidade = cidade
        self.eventos_confirmados: list[str] = []  # lista de nomes de eventos

    def confirmar_evento(self, nome_evento: str):
        if nome_evento not in self.eventos_confirmados:
            self.eventos_confirmados.append(nome_evento)

    def cancelar_evento(self, nome_evento: str):
        if nome_evento in self.eventos_confirmados:
            self.eventos_confirmados.remove(nome_evento)

    def __str__(self):
        return (f"Nome: {self.nome} | Email: {self.email} | "
                f"Telefone: {self.telefone} | Cidade: {self.cidade}")