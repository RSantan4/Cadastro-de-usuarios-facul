from models.evento import CATEGORIAS
from datetime import datetime

def limpar():
    print("\n" + "=" * 55)

def cabecalho(titulo: str):
    limpar()
    print(f"  🎉  {titulo.upper()}")
    print("=" * 55)

def menu_principal():
    cabecalho("Sistema de Eventos")
    print("  [1] Cadastrar usuário")
    print("  [2] Cadastrar evento")
    print("  [3] Listar todos os eventos")
    print("  [4] Participar de um evento")
    print("  [5] Meus eventos confirmados")
    print("  [6] Cancelar participação")
    print("  [0] Sair")
    print("=" * 55)
    return input("  Escolha: ").strip()

def input_usuario():
    cabecalho("Cadastro de Usuário")
    nome = input("  Nome     : ").strip()
    email = input("  Email    : ").strip()
    telefone = input("  Telefone : ").strip()
    cidade = input("  Cidade   : ").strip()
    return nome, email, telefone, cidade

def input_evento():
    cabecalho("Cadastro de Evento")
    nome = input("  Nome do evento : ").strip()
    endereco = input("  Endereço       : ").strip()

    print("\n  Categorias disponíveis:")
    for i, cat in enumerate(CATEGORIAS, 1):
        print(f"    [{i}] {cat}")
    while True:
        try:
            idx = int(input("  Escolha a categoria: ")) - 1
            categoria = CATEGORIAS[idx]
            break
        except (ValueError, IndexError):
            print("  ❌ Opção inválida.")

    while True:
        horario = input("  Horário (dd/mm/aaaa HH:MM): ").strip()
        try:
            datetime.strptime(horario, "%d/%m/%Y %H:%M")
            break
        except ValueError:
            print("  ❌ Formato inválido. Use dd/mm/aaaa HH:MM")

    descricao = input("  Descrição      : ").strip()
    return nome, endereco, categoria, horario, descricao

def exibir_eventos(eventos, titulo="Eventos Disponíveis"):
    cabecalho(titulo)
    if not eventos:
        print("  Nenhum evento encontrado.")
        return
    for i, e in enumerate(eventos, 1):
        print(f"\n  [{i}]{e}")
        print("  " + "-" * 50)