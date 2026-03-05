from datetime import datetime

CATEGORIAS = ["Festa", "Show", "Evento Esportivo", "Teatro", "Exposição", "Outro"]

class Evento:
    FORMATO_DATA = "%d/%m/%Y %H:%M"

    def __init__(self, nome: str, endereco: str, categoria: str,
                 horario: str, descricao: str):
        self.nome = nome
        self.endereco = endereco
        self.categoria = categoria
        self.horario_str = horario  # salvo como string para serialização
        self.descricao = descricao

    @property
    def horario(self) -> datetime:
        return datetime.strptime(self.horario_str, self.FORMATO_DATA)

    def status(self) -> str:
        agora = datetime.now()
        diff = (self.horario - agora).total_seconds() / 60  # em minutos

        if diff < -60:
            return "✅ Já ocorreu"
        elif -60 <= diff <= 0:
            return "🔴 Ocorrendo agora!"
        elif diff <= 1440:
            horas = int(diff // 60)
            minutos = int(diff % 60)
            return f"⏳ Em {horas}h {minutos}min"
        else:
            return f"📅 {self.horario.strftime(self.FORMATO_DATA)}"

    def to_data_line(self) -> str:
        return f"{self.nome}|{self.endereco}|{self.categoria}|{self.horario_str}|{self.descricao}"

    @classmethod
    def from_data_line(cls, line: str):
        partes = line.strip().split("|")
        if len(partes) == 5:
            return cls(*partes)
        return None

    def __str__(self):
        return (f"\n  📌 Nome     : {self.nome}"
                f"\n  📍 Endereço : {self.endereco}"
                f"\n  🏷️  Categoria : {self.categoria}"
                f"\n  🕐 Horário  : {self.horario_str}"
                f"\n  📋 Descrição: {self.descricao}"
                f"\n  🔔 Status   : {self.status()}")