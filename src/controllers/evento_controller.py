import os
from models.evento import Evento

CAMINHO_ARQUIVO = "data/events.data"

class EventoController:
    def __init__(self):
        self.eventos: list[Evento] = []
        self._garantir_arquivo()
        self.carregar_eventos()

    def _garantir_arquivo(self):
        os.makedirs("data", exist_ok=True)
        if not os.path.exists(CAMINHO_ARQUIVO):
            open(CAMINHO_ARQUIVO, "w").close()

    def carregar_eventos(self):
        self.eventos = []
        with open(CAMINHO_ARQUIVO, "r", encoding="utf-8") as f:
            for linha in f:
                evento = Evento.from_data_line(linha)
                if evento:
                    self.eventos.append(evento)

    def salvar_eventos(self):
        with open(CAMINHO_ARQUIVO, "w", encoding="utf-8") as f:
            for evento in self.eventos:
                f.write(evento.to_data_line() + "\n")

    def cadastrar_evento(self, nome, endereco, categoria, horario, descricao):
        evento = Evento(nome, endereco, categoria, horario, descricao)
        self.eventos.append(evento)
        self.salvar_eventos()

    def listar_ordenados(self) -> list[Evento]:
        return sorted(self.eventos, key=lambda e: e.horario)

    def buscar_por_nome(self, nome: str):
        for e in self.eventos:
            if e.nome.lower() == nome.lower():
                return e
        return None