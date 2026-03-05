from controllers.evento_controller import EventoController
from controllers.usuario_controller import UsuarioController
from views.menu import (menu_principal, input_usuario,
                        input_evento, exibir_eventos, cabecalho)

def main():
    evento_ctrl = EventoController()
    usuario_ctrl = UsuarioController()

    print("\n  ✅ Eventos carregados do arquivo.")

    while True:
        opcao = menu_principal()

        # ── 1. Cadastrar usuário ──────────────────────────
        if opcao == "1":
            dados = input_usuario()
            usuario_ctrl.cadastrar(*dados)
            print(f"\n  ✅ Usuário '{dados[0]}' cadastrado com sucesso!")

        # ── 2. Cadastrar evento ───────────────────────────
        elif opcao == "2":
            if not usuario_ctrl.logado():
                print("\n  ⚠️  Cadastre um usuário primeiro.")
                continue
            dados = input_evento()
            evento_ctrl.cadastrar_evento(*dados)
            print(f"\n  ✅ Evento '{dados[0]}' cadastrado e salvo!")

        # ── 3. Listar eventos ─────────────────────────────
        elif opcao == "3":
            eventos = evento_ctrl.listar_ordenados()
            exibir_eventos(eventos)

        # ── 4. Participar de evento ───────────────────────
        elif opcao == "4":
            if not usuario_ctrl.logado():
                print("\n  ⚠️  Cadastre um usuário primeiro.")
                continue
            eventos = evento_ctrl.listar_ordenados()
            exibir_eventos(eventos, "Participar de Evento")
            nome = input("\n  Digite o nome do evento: ").strip()
            evento = evento_ctrl.buscar_por_nome(nome)
            if evento:
                usuario_ctrl.confirmar_participacao(evento.nome)
                print(f"\n  ✅ Presença confirmada em '{evento.nome}'!")
            else:
                print("\n  ❌ Evento não encontrado.")

        # ── 5. Meus eventos ───────────────────────────────
        elif opcao == "5":
            if not usuario_ctrl.logado():
                print("\n  ⚠️  Cadastre um usuário primeiro.")
                continue
            cabecalho("Meus Eventos Confirmados")
            meus = usuario_ctrl.meus_eventos()
            if not meus:
                print("  Nenhum evento confirmado.")
            else:
                for nome in meus:
                    ev = evento_ctrl.buscar_por_nome(nome)
                    if ev:
                        print(ev)

        # ── 6. Cancelar participação ──────────────────────
        elif opcao == "6":
            if not usuario_ctrl.logado():
                print("\n  ⚠️  Cadastre um usuário primeiro.")
                continue
            meus = usuario_ctrl.meus_eventos()
            if not meus:
                print("\n  Você não tem eventos confirmados.")
                continue
            cabecalho("Cancelar Participação")
            for i, nome in enumerate(meus, 1):
                print(f"  [{i}] {nome}")
            nome = input("\n  Nome do evento para cancelar: ").strip()
            usuario_ctrl.cancelar_participacao(nome)
            print(f"\n  ✅ Participação em '{nome}' cancelada.")

        # ── 0. Sair ───────────────────────────────────────
        elif opcao == "0":
            print("\n  👋 Até logo!\n")
            break

        else:
            print("\n  ❌ Opção inválida.")

        input("\n  [Enter para continuar]")

if __name__ == "__main__":
    main()