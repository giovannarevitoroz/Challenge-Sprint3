#Membros:

#Giovanna Revito Roz - RM558981
#Kaian Gustavo de Oliveira Nascimento - RM558986
#Lucas Kenji Kikuchi - RM554424

#Link do vídeo: https://youtu.be/UF3JYBidHyY?si=-Cn2rw9XjtLmpyH7

# módulo importado para utilização de regex
import re
# módulo importado para utilização de uuid
import uuid

# declaração de variáveis
problema = ''
usuarios = [{'nome': 'Carlos', 'email': 'carlos@gmail.com', 'senha': '12345', 'cpf': '111.222.333-44', 'telefone': '11 92893-2938'}]
veiculos = [{'marca': 'Chevrolet', 'modelo': 'Corsa', 'ano': '2002', 'placa': 'ABC-1D23', 'quilometragem': 50000.0, 'cpf_proprietario': '111.222.333-44'}]
agendamentos = []
diagnosticos = []
funcionarios = []
orcamentos = []
# problemas do carro e soluções
problemas_carro = {
    "Bateria fraca": {
        "sintomas": {"bateria": 4, "liga": 4, "partida": 2, "luz": 2, "clique": 1, "ruído": 1},
        "solução": "Verificar e substituir a bateria se necessário, verificar o sistema de carregamento."
    },
    "Filtro de ar sujo": {
        "sintomas": {"potência": 3, "partida": 2, "combustível": 2, "odor": 2, "cheiro": 2, "fumaça": 1},
        "solução": "Substituir o filtro de ar para melhorar o desempenho do motor e a eficiência do combustível."
    },
    "Falha na vela de ignição": {
        "sintomas": {"engasgando": 3, "partida": 2, "devagar": 1, "combustível": 1, "marcha": 1},
        "solução": "Substituir as velas de ignição e verificar o sistema de ignição para garantir uma partida eficiente do motor."
    },
    "Pneu descalibrado": {
        "sintomas": {"puxar": 4, "direção": 3, "pneu": 3, "volante": 2, "desgaste": 2, "vibrar": 1},
        "solução": "Calibrar os pneus e verificar o alinhamento."
    },
    "Alternador com defeito": {
        "sintomas": {"luz": 3, "partida": 2, "potência": 2, "bateria": 1, "ruído": 1, "farol": 1},
        "solução": "Testar e substituir o alternador se necessário para garantir o funcionamento correto dos sistemas elétricos."
    },
    "Falta de óleo no motor": {
        "sintomas": {"luz": 3, "painel": 3, "óleo": 2, "motor": 2, "ruído": 2, "combustível": 1},
        "solução": "Verificar e completar o nível de óleo ou realizar troca completa do óleo do motor."
    },
    "Radiador com vazamento": {
        "sintomas": {"vazamento": 4, "poça": 3, "fluido": 2, "superaquecendo": 2, "sobreaquecendo": 2, "queimado": 1, "mancha": 1},
        "solução": "Reparar ou substituir o radiador e completar o fluido de arrefecimento para evitar o superaquecimento do motor."
    },
    "Correia dentada desgastada": {
        "sintomas": {"ruído": 3, "engasgando": 2, "desalinhamento": 2, "morrendo": 1, "partida": 1, "superaquecendo": 1, "sobreaquecendo": 1},
        "solução": "Substituir a correia dentada e verificar o tensionamento para evitar problemas no motor."
    },
    "Problema na transmissão": {
        "sintomas": {"troca": 4, "marcha": 3, "ruído": 2, "tranco": 2, "patinando": 2, "superaquecendo": 1, "sobreaquecendo": 1},
        "solução": "Verificar e reparar a transmissão, trocar o fluido de transmissão para garantir uma troca de marchas suave."
    },
    "Falha na bomba de combustível": {
        "sintomas": {"liga": 3, "potência": 2, "motor": 1, "ruído": 2, "combustível": 1, "falha": 2},
        "solução": "Testar e substituir a bomba de combustível para garantir que o motor receba o combustível adequado."
    },
    "Disco de freio desgastado": {
        "sintomas": {"ruído": 3, "freio": 3, "frenagem": 2, "vibração": 2, "fumaça": 2, "desgaste": 2},
        "solução": "Substituir os discos de freio e as pastilhas se necessário para garantir a eficiência dos freios."
    },
    "Suspensão danificada": {
        "sintomas": {"balançando": 3, "ruído": 2, "pneus": 1, "instável": 2, "direção": 2, "baixo": 2},
        "solução": "Verificar e substituir os componentes da suspensão para garantir uma condução estável e segura."
    },
    "Bomba de água com defeito": {
        "sintomas": {"superaquecendo": 3, "sobreaquecendo": 3, "vazamento": 2, "barulho": 1, "motor": 2, "aquecendo": 1, "fumaça": 2},
        "solução": "Substituir a bomba de água e verificar o sistema de arrefecimento para evitar o superaquecimento do motor."
    },
    "Sensor de oxigênio defeituoso": {
        "sintomas": {"combustível": 2, "motor": 2, "luz": 1, "emissões": 2, "gases": 1, "falha": 2},
        "solução": "Substituir o sensor de oxigênio para manter o desempenho do motor e reduzir as emissões."
    },
    "Problema no escapamento": {
        "sintomas": {"ruído": 2, "gases": 2, "potência": 1, "fumaça": 2, "escapamento": 1, "baixo": 2},
        "solução": "Verificar e reparar o sistema de escapamento para garantir a eliminação adequada dos gases do motor."
    },
    "Problema na embreagem": {
        "sintomas": {"marchas": 3, "pedal": 2, "trepidando": 1, "embreagem": 2, "troca": 1, "fumaça": 2},
        "solução": "Substituir o conjunto da embreagem para garantir o funcionamento adequado das marchas."
    },
    "Amortecedores desgastados": {
        "sintomas": {"instável": 3, "pneus": 2, "balançando": 1, "ruído": 2, "lombadas": 1, "baixo": 2},
        "solução": "Substituir os amortecedores para melhorar a estabilidade e o conforto da condução."
    },
    "Problema na bomba de direção": {
        "sintomas": {"direção": 3, "ruído": 2, "fluido": 1, "volante": 2, "vibração": 2, "duro": 2},
        "solução": "Verificar e reparar a bomba de direção hidráulica, completar fluido para garantir a direção suave."
    },
    "Sensor de temperatura com defeito": {
        "sintomas": {"superaquecendo": 3, "sobreaquecendo": 3, "luz": 2, "ventoinha": 1, "temperatura": 2, "motor": 1, "baixo": 2},
        "solução": "Substituir o sensor de temperatura para evitar problemas de superaquecimento e garantir o funcionamento correto do motor."
    },
    "Vazamento de óleo": {
        "sintomas": {"vazamento": 4, "óleo": 3, "mancha": 2, "fumaça": 2, "cheiro": 2, "luz": 1},
        "solução": "Identificar e reparar o vazamento de óleo, completar o nível de óleo para evitar danos ao motor."
    },
    "Problema no filtro de combustível": {
        "sintomas": {"potência": 2, "aceleração": 2, "motor": 1, "luz": 2, "combustível": 1, "entupido": 2},
        "solução": "Verificar e substituir o filtro de combustível para garantir o fluxo de combustível adequado para o motor."
    },
    "Problema no sensor de pressão dos pneus": {
        "sintomas": {"TPMS": 2, "pressão": 2, "pneus": 1, "vibração": 2, "leitura": 1, "baixo": 2},
        "solução": "Verificar e substituir o sensor de pressão dos pneus para garantir leituras precisas e evitar problemas com os pneus."
    },
    "Bobina de ignição defeituosa": {
        "sintomas": {"motor": 3, "partida": 2, "luz": 1, "potência": 2, "ignição": 1, "falha": 2},
        "solução": "Substituir a bobina de ignição para garantir uma ignição eficiente e melhorar o desempenho do motor."
    },
    "Falha no módulo ABS": {
        "sintomas": {"ABS": 3, "frenagem": 2, "vibração": 1, "freio": 2, "controle": 2, "baixo": 2},
        "solução": "Reparar ou substituir o módulo ABS para garantir a funcionalidade adequada do sistema de frenagem."
    },
    "Problema no termostato": {
        "sintomas": {"superaquecendo": 3, "sobreaquecendo": 3, "aquecimento": 2, "motor": 1, "temperatura": 2, "termostato": 1, "baixo": 2},
        "solução": "Substituir o termostato para garantir a regulação correta da temperatura do motor e evitar superaquecimento."
    },
    "Falha na bomba de vácuo": {
        "sintomas": {"freios": 3, "frear": 2, "potência": 1, "ruído": 2, "vácuo": 1, "baixo": 2},
        "solução": "Substituir a bomba de vácuo para garantir a assistência adequada aos freios e evitar problemas na frenagem."
    },
    "Vazamento no sistema de exaustão": {
        "sintomas": {"ruído": 2, "gases": 2, "potência": 1, "fumaça": 2, "exaustão": 1, "baixo": 2},
        "solução": "Reparar o vazamento no sistema de exaustão para garantir a eliminação adequada dos gases e reduzir o ruído."
    },
    "Problema na válvula EGR": {
        "sintomas": {"potência": 2, "emissões": 2, "motor": 1, "luz": 2, "EGR": 4, "baixo": 2},
        "solução": "Limpar ou substituir a válvula EGR para melhorar a eficiência do motor e reduzir as emissões."
    },
    "Falha no sistema de arrefecimento": {
        "sintomas": {"superaquecendo": 3, "sobreaquecendo": 3, "vazamento": 2, "vapor": 1, "motor": 2, "arrefecimento": 1, "baixo": 2},
        "solução": "Verificar e reparar o sistema de arrefecimento, substituir componentes defeituosos para evitar o superaquecimento do motor."
    },
    "Problema no ar condicionado": {
        "sintomas": {"ar-condicionado": 5, "resfriamento": 4,"barulho": 4,"cheiro": 3,"temperatura": 3},
        "solução": "Verificar e reparar o sistema de ar condicionado, incluindo checagem de vazamentos, substituição de filtros, e manutenção dos componentes do sistema."
    },
    "Problema de Alinhamento e Geometria": {
        "sintomas": {"volante": 4, "puxando": 4, "desgaste": 3, "vibrando": 2, "direção": 2, "instabilidade": 2, "desalinhado": 1},
        "solução": "Verificar e ajustar o alinhamento e a geometria das rodas para garantir uma direção estável e uniforme. Se necessário, substituir componentes desgastados."
    }

}
# servicos 
servicos = [
    {"id_servico": "SV0001", "descricao_servico": "Verificar e substituir a bateria se necessário, verificar o sistema de carregamento.", "tipo_servico": "Troca de Bateria", "preco": 150.00, "duracao": "1-2 horas"},
    {"id_servico": "SV0002", "descricao_servico": "Substituir o filtro de ar para melhorar o desempenho do motor e a eficiência do combustível.", "tipo_servico": "Substituição do Filtro de Ar", "preco": 80.00, "duracao": "0.5-1 hora"},
    {"id_servico": "SV0003", "descricao_servico": "Substituir as velas de ignição e verificar o sistema de ignição para garantir uma partida eficiente do motor.", "tipo_servico": "Troca das Velas de Ignição", "preco": 100.00, "duracao": "1-2 horas"},
    {"id_servico": "SV0004", "descricao_servico": "Calibrar os pneus e verificar o alinhamento.", "tipo_servico": "Calibração e Alinhamento de Pneus", "preco": 120.00, "duracao": "1-2 horas"},
    {"id_servico": "SV0005", "descricao_servico": "Testar e substituir o alternador se necessário para garantir o funcionamento correto dos sistemas elétricos.", "tipo_servico": "Substituição do Alternador", "preco": 250.00, "duracao": "2-4 horas"},
    {"id_servico": "SV0006", "descricao_servico": "Verificar e completar o nível de óleo ou realizar troca completa do óleo do motor.", "tipo_servico": "Troca de Óleo", "preco": 90.00, "duracao": "0.5-1 hora"},
    {"id_servico": "SV0007", "descricao_servico": "Reparar ou substituir o radiador e completar o fluido de arrefecimento para evitar o superaquecimento do motor.", "tipo_servico": "Reparo do Radiador", "preco": 300.00, "duracao": "2-3 horas"},
    {"id_servico": "SV0008", "descricao_servico": "Substituir a correia dentada e verificar o tensionamento para evitar problemas no motor.", "tipo_servico": "Substituição da Correia Dentada", "preco": 250.00, "duracao": "2-3 horas"},
    {"id_servico": "SV0009", "descricao_servico": "Verificar e reparar a transmissão, trocar o fluido de transmissão para garantir uma troca de marchas suave.", "tipo_servico": "Reparo da Transmissão", "preco": 350.00, "duracao": "3-4 horas"},
    {"id_servico": "SV0010", "descricao_servico": "Testar e substituir a bomba de combustível para garantir que o motor receba o combustível adequado.", "tipo_servico": "Substituição da Bomba de Combustível", "preco": 200.00, "duracao": "1-2 horas"},
    {"id_servico": "SV0011", "descricao_servico": "Substituir os discos de freio e as pastilhas se necessário para garantir a eficiência dos freios.", "tipo_servico": "Substituição dos Discos de Freio", "preco": 250.00, "duracao": "2-3 horas"},
    {"id_servico": "SV0012", "descricao_servico": "Verificar e substituir os componentes da suspensão para garantir uma condução estável e segura.", "tipo_servico": "Reparo da Suspensão", "preco": 300.00, "duracao": "2-3 horas"},
    {"id_servico": "SV0013", "descricao_servico": "Substituir a bomba de água e verificar o sistema de arrefecimento para evitar o superaquecimento do motor.", "tipo_servico": "Substituição da Bomba de Água", "preco": 200.00, "duracao": "1.5-2 horas"},
    {"id_servico": "SV0014", "descricao_servico": "Substituir o sensor de oxigênio para manter o desempenho do motor e reduzir as emissões.", "tipo_servico": "Substituição do Sensor de Oxigênio", "preco": 130.00, "duracao": "1-1.5 horas"},
    {"id_servico": "SV0015", "descricao_servico": "Verificar e reparar o sistema de escapamento para garantir a eliminação adequada dos gases do motor.", "tipo_servico": "Reparo do Sistema de Escapamento", "preco": 200.00, "duracao": "1-2 horas"},
    {"id_servico": "SV0016", "descricao_servico": "Substituir o conjunto da embreagem para garantir o funcionamento adequado das marchas.", "tipo_servico": "Substituição da Embreagem", "preco": 400.00, "duracao": "3-4 horas"},
    {"id_servico": "SV0017", "descricao_servico": "Substituir os amortecedores para melhorar a estabilidade e o conforto da condução.", "tipo_servico": "Substituição dos Amortecedores", "preco": 350.00, "duracao": "2-3 horas"},
    {"id_servico": "SV0018", "descricao_servico": "Verificar e reparar a bomba de direção hidráulica, completar fluido para garantir a direção suave.", "tipo_servico": "Reparo da Bomba de Direção", "preco": 150.00, "duracao": "1-1.5 horas"},
    {"id_servico": "SV0019", "descricao_servico": "Substituir o sensor de temperatura para evitar problemas de superaquecimento e garantir o funcionamento correto do motor.", "tipo_servico": "Substituição do Sensor de Temperatura", "preco": 120.00, "duracao": "1-1.5 horas"},
    {"id_servico": "SV0020", "descricao_servico": "Identificar e reparar o vazamento de óleo, completar o nível de óleo para evitar danos ao motor.", "tipo_servico": "Reparo de Vazamento de Óleo", "preco": 180.00, "duracao": "1-2 horas"},
    {"id_servico": "SV0021", "descricao_servico": "Verificar e substituir o filtro de combustível para garantir o fluxo de combustível adequado para o motor.", "tipo_servico": "Substituição do Filtro de Combustível", "preco": 100.00, "duracao": "1-1.5 horas"},
    {"id_servico": "SV0022", "descricao_servico": "Verificar e substituir o sensor de pressão dos pneus para garantir leituras precisas e evitar problemas com os pneus.", "tipo_servico": "Substituição do Sensor de Pressão dos Pneus", "preco": 120.00, "duracao": "1-1.5 horas"},
    {"id_servico": "SV0023", "descricao_servico": "Substituir a bobina de ignição para garantir uma ignição eficiente e melhorar o desempenho do motor.", "tipo_servico": "Substituição da Bobina de Ignição", "preco": 140.00, "duracao": "1-1.5 horas"},
    {"id_servico": "SV0024", "descricao_servico": "Reparar ou substituir o módulo ABS para garantir a funcionalidade adequada do sistema de frenagem.", "tipo_servico": "Reparo do Módulo ABS", "preco": 350.00, "duracao": "2-3 horas"},
    {"id_servico": "SV0025", "descricao_servico": "Substituir o termostato para garantir a regulação correta da temperatura do motor e evitar superaquecimento.", "tipo_servico": "Substituição do Termostato", "preco": 130.00, "duracao": "1-1.5 horas"},
    {"id_servico": "SV0026", "descricao_servico": "Substituir a bomba de vácuo para garantir a assistência adequada aos freios e evitar problemas na frenagem.", "tipo_servico": "Substituição da Bomba de Vácuo", "preco": 180.00, "duracao": "1-1.5 horas"},
    {"id_servico": "SV0027", "descricao_servico": "Reparar o vazamento no sistema de exaustão para garantir a eliminação adequada dos gases e reduzir o ruído.", "tipo_servico": "Reparo do Sistema de Exaustão", "preco": 200.00, "duracao": "1-2 horas"},
    {"id_servico": "SV0028", "descricao_servico": "Limpar ou substituir a válvula EGR para melhorar a eficiência do motor e reduzir as emissões.", "tipo_servico": "Limpeza ou Substituição da Válvula EGR", "preco": 150.00, "duracao": "1-1.5 horas"},
    {"id_servico": "SV0029", "descricao_servico": "Verificar e reparar o sistema de arrefecimento, substituir componentes defeituosos para evitar o superaquecimento do motor.", "tipo_servico": "Reparo do Sistema de Arrefecimento", "preco": 300.00, "duracao": "2-3 horas"},
    {"id_servico": "SV0030","descricao_servico": "Verificar e reparar o sistema de ar condicionado, incluindo checagem de vazamentos, substituição de filtros, e manutenção dos componentes do sistema.","tipo_servico": "Reparo do Sistema de Ar Condicionado","preco": 250.00,"duracao": "2-3 horas"},
    {"id_servico": "SV0031","descricao_servico": "Verificar e ajustar o alinhamento e a geometria das rodas para garantir uma direção estável e uniforme. Se necessário, substituir componentes desgastados.","tipo_servico": "Ajuste da Geometria/Alinhamento","preco": 170.00,"duracao": "1-2 horas"}
]
#peças e produtos
pecas = [
    {"id_peca": 'P00001', "nome_peca": "Bateria", "quantidade_disponivel": 20, "preco": 450.00},
    {"id_peca": 'P00002', "nome_peca": "Filtro de ar", "quantidade_disponivel": 35, "preco": 50.00},
    {"id_peca": 'P00003', "nome_peca": "Vela de ignição", "quantidade_disponivel": 60, "preco": 30.00},
    {"id_peca": 'P00004', "nome_peca": "Pneu", "quantidade_disponivel": 80, "preco": 300.00},
    {"id_peca": 'P00005', "nome_peca": "Alternador", "quantidade_disponivel": 15, "preco": 800.00},
    {"id_peca": 'P00006', "nome_peca": "Óleo de motor", "quantidade_disponivel": 100, "preco": 45.00},
    {"id_peca": 'P00007', "nome_peca": "Radiador", "quantidade_disponivel": 10, "preco": 600.00},
    {"id_peca": 'P00008', "nome_peca": "Correia dentada", "quantidade_disponivel": 25, "preco": 150.00},
    {"id_peca": 'P00009', "nome_peca": "Fluido de transmissão", "quantidade_disponivel": 40, "preco": 120.00},
    {"id_peca": 'P00010', "nome_peca": "Bomba de combustível", "quantidade_disponivel": 12, "preco": 500.00},
    {"id_peca": 'P00011', "nome_peca": "Disco de freio", "quantidade_disponivel": 50, "preco": 200.00},
    {"id_peca": 'P00012', "nome_peca": "Amortecedor", "quantidade_disponivel": 30, "preco": 250.00},
    {"id_peca": 'P00013', "nome_peca": "Bomba de água", "quantidade_disponivel": 20, "preco": 350.00},
    {"id_peca": 'P00014', "nome_peca": "Sensor de oxigênio", "quantidade_disponivel": 15, "preco": 250.00},
    {"id_peca": 'P00015', "nome_peca": "Tubo de escape", "quantidade_disponivel": 10, "preco": 100.00},
    {"id_peca": 'P00016', "nome_peca": "Conjunto de embreagem", "quantidade_disponivel": 18, "preco": 800.00},
    {"id_peca": 'P00017', "nome_peca": "Injetores de combustível", "quantidade_disponivel": 25, "preco": 400.00},
    {"id_peca": 'P00018', "nome_peca": "Bomba de direção hidráulica", "quantidade_disponivel": 8, "preco": 600.00},
    {"id_peca": 'P00019', "nome_peca": "Sensor de temperatura", "quantidade_disponivel": 20, "preco": 180.00},
    {"id_peca": 'P00020', "nome_peca": "Cabo de vela", "quantidade_disponivel": 40, "preco": 90.00},
    {"id_peca": 'P00021', "nome_peca": "Correia do alternador", "quantidade_disponivel": 30, "preco": 140.00},
    {"id_peca": 'P00022', "nome_peca": "Filtro de combustível", "quantidade_disponivel": 45, "preco": 60.00},
    {"id_peca": 'P00023', "nome_peca": "Sensor de pressão dos pneus", "quantidade_disponivel": 12, "preco": 230.00},
    {"id_peca": 'P00024', "nome_peca": "Bobina de ignição", "quantidade_disponivel": 25, "preco": 350.00},
    {"id_peca": 'P00025', "nome_peca": "Módulo ABS", "quantidade_disponivel": 0, "preco": 1500.00},
    {"id_peca": 'P00026', "nome_peca": "Termostato", "quantidade_disponivel": 20, "preco": 120.00},
    {"id_peca": 'P00027', "nome_peca": "Válvula PCV", "quantidade_disponivel": 30, "preco": 70.00},
    {"id_peca": 'P00028', "nome_peca": "Eixo de transmissão", "quantidade_disponivel": 7, "preco": 900.00},
    {"id_peca": 'P00029', "nome_peca": "Bomba de vácuo", "quantidade_disponivel": 10, "preco": 450.00},
    {"id_peca": 'P00030', "nome_peca": "Coletor de escape", "quantidade_disponivel": 8, "preco": 2000.00},
    {"id_peca": 'P00031', "nome_peca": "Válvula EGR", "quantidade_disponivel": 15, "preco": 220.00},
    {"id_peca": 'P00032', "nome_peca": "Mangueira de radiador", "quantidade_disponivel": 10, "preco": 250.00}
]
#marcas disponiveis de veiculos
marcas = {
        'Chevrolet': {'Corsa': [1995, 2002, 2009], 'Onix': [2012, 2017, 2021], 'Prisma': [2006, 2013, 2018]},
        'Ford':{'Ka': [1997, 2005, 2012], 'EcoSport': [2003, 2010, 2018], 'Focus': [1998, 2008, 2015]},
        'Honda': {'Civic': [1996, 2004, 2011], 'Fit': [2001, 2008, 2015], 'HR-V': [2014, 2017, 2020]},
        'Volkswagen': {'Gol': [1980, 1998, 2012], 'Polo': [1995, 2002, 2010], 'Jetta': [1984, 1999, 2007]},
        'Toyota': {'Corolla': [1990, 2002, 2010], 'Hilux': [1998, 2005, 2013], 'Etios': [2010, 2015, 2020]},
        'Hyundai': {'HB20': [2012, 2016, 2020], 'Tucson': [2004, 2010, 2016], 'Creta': [2014, 2018, 2022]},
        'Fiat': {'Uno': [1983, 1996, 2004], 'Argo': [2017, 2020, 2023], 'Toro': [2016, 2019, 2022]},
        'Nissan': {'March': [2002, 2010, 2018], 'Versa': [2006, 2013, 2020], 'Kicks': [2016, 2019, 2023]},
        'Jeep': {'Renegade': [2014, 2017, 2021], 'Compass': [2006, 2012, 2018], 'Grand Cherokee': [1992, 2005, 2014]},
        'Renault': {'Kwid': [2015, 2018, 2022], 'Sandero': [2008, 2014, 2019], 'Duster': [2010, 2015, 2020]}
    }
# centros automotivos da porto
centros = [
    {"id_centro": "C00001", "nome_centro": "Lins de Vasconcelos", "endereco_centro": "Av. Lins de Vasconcelos, 2474 - Vila Mariana, São Paulo - SP, 04112-001", "telefone_centro": "(11) 1234-5678", "horario_funcionamento": "08:00 - 18:00"},
    {"id_centro": "C00002", "nome_centro": "Conceição", "endereco_centro": "Av. Diederichsen, 1426 - Vila Guarani (Zona Sul), São Paulo - SP, 04310-001", "telefone_centro": "(11) 2345-6789", "horario_funcionamento": "08:00 - 18:00"},
    {"id_centro": "C00003", "nome_centro": "São Caetano do Sul", "endereco_centro": "Av. Sen. Roberto Símonsen, 1305 - Cerâmica, São Caetano do Sul - SP, 09530-402", "telefone_centro": "(11) 3456-7890", "horario_funcionamento": "08:00 - 18:00"},
    {"id_centro": "C00004", "nome_centro": "Jardim Paulista", "endereco_centro": "Av. Brigadeiro Luís Antônio, 3383 - Jardim Paulista, São Paulo - SP, 01401-001", "telefone_centro": "(11) 4567-8901", "horario_funcionamento": "08:00 - 18:00"},
    {"id_centro": "C00005", "nome_centro": "Ipiranga", "endereco_centro": "R. Silva Bueno, 1176 - Ipiranga, São Paulo - SP, 04208-000", "telefone_centro": "(11) 5678-9012", "horario_funcionamento": "08:00 - 18:00"},
    {"id_centro": "C00006", "nome_centro": "Bosque da Saúde", "endereco_centro": "Av. Bosque da Saúde, 1276 - Jabaquara, São Paulo - SP, 04142-082", "telefone_centro": "(11) 6789-0123", "horario_funcionamento": "08:00 - 18:00"},
    {"id_centro": "C00007", "nome_centro": "Vila Mariana", "endereco_centro": "R. França Pinto, 1115 - Vila Mariana, São Paulo - SP, 04016-034", "telefone_centro": "(11) 7890-1234", "horario_funcionamento": "08:00 - 18:00"},
    {"id_centro": "C00008", "nome_centro": "Campo Belo", "endereco_centro": "R. Otávio Tarquínio de Sousa, 304 - Campo Belo, São Paulo - SP, 04613-000", "telefone_centro": "(11) 8901-2345", "horario_funcionamento": "08:00 - 18:00"},
    {"id_centro": "C00009", "nome_centro": "Planalto Paulista", "endereco_centro": "Av. dos Bandeirantes, 5024 - Planalto Paulista, São Paulo - SP, 04071-000", "telefone_centro": "(11) 9012-3456", "horario_funcionamento": "08:00 - 18:00"},
    {"id_centro": "C00010", "nome_centro": "Mooca", "endereco_centro": "Rua dos Trilhos, 1327 - Alto da Mooca, São Paulo - SP, 03168-009", "telefone_centro": "(11) 0123-4567", "horario_funcionamento": "08:00 - 18:00"}
]
# cargos 
cargos = [
    {"id_cargo": "CG0001", "nome_cargo": "Mecânico", "area_cargo": "Manutenção", "descricao_cargo": "Responsável por realizar manutenção e reparos em veículos, diagnosticando problemas mecânicos e substituindo peças defeituosas.", "salario_cargo": 3500.00},
    {"id_cargo": "CG0002", "nome_cargo": "Eletricista Automotivo", "area_cargo": "Manutenção Elétrica", "descricao_cargo": "Especialista em sistemas elétricos de veículos, realiza diagnósticos e reparos em circuitos elétricos, baterias e sistemas de iluminação.", "salario_cargo": 3200.00},
    {"id_cargo": "CG0003", "nome_cargo": "Alinhador/Balanceador", "area_cargo": "Manutenção de Pneus", "descricao_cargo": "Responsável pelo alinhamento e balanceamento das rodas dos veículos, garantindo uma direção estável e segura.", "salario_cargo": 2800.00},
    {"id_cargo": "CG0004", "nome_cargo": "Consultor Técnico", "area_cargo": "Atendimento ao Cliente", "descricao_cargo": "Atende os clientes, realiza diagnósticos preliminares e oferece soluções técnicas para problemas automotivos.", "salario_cargo": 4000.00},
    {"id_cargo": "CG0005", "nome_cargo": "Gerente de Oficina", "area_cargo": "Gestão", "descricao_cargo": "Gerencia a equipe de manutenção, controla o fluxo de trabalho, e garante a qualidade dos serviços prestados.", "salario_cargo": 6000.00},
    {"id_cargo": "CG0006", "nome_cargo": "Recepcionista", "area_cargo": "Atendimento ao Cliente", "descricao_cargo": "Responsável por receber clientes, agendar serviços e direcionar as demandas para os profissionais adequados.", "salario_cargo": 2000.00},
    {"id_cargo": "CG0007", "nome_cargo": "Pintor Automotivo", "area_cargo": "Pintura", "descricao_cargo": "Especialista em repintura de veículos, realiza o trabalho de preparação, pintura e acabamento em superfícies automotivas.", "salario_cargo": 3700.00},
    {"id_cargo": "CG0008", "nome_cargo": "Auxiliar de Serviços Gerais", "area_cargo": "Manutenção Geral", "descricao_cargo": "Apoia na limpeza e organização da oficina, além de auxiliar nas tarefas básicas de manutenção.", "salario_cargo": 1800.00},
    {"id_cargo": "CG0009", "nome_cargo": "Auxiliar de Almoxarifado", "area_cargo": "Logística", "descricao_cargo": "Responsável por organizar o estoque de peças e materiais, realizar inventários e fornecer os itens necessários à equipe técnica.", "salario_cargo": 2200.00},
    {"id_cargo": "CG0010", "nome_cargo": "Supervisor de Qualidade", "area_cargo": "Qualidade", "descricao_cargo": "Monitora a qualidade dos serviços realizados, garantindo que os padrões sejam cumpridos e propondo melhorias nos processos.", "salario_cargo": 4500.00}
]

# expressões regulares
regexCpf = r'^\d{3}\.\d{3}\.\d{3}-\d{2}$'
regexTel = r"^(\(\d{2}\) \d{4,5}-\d{4}|\d{2} \d{4,5}-\d{4})$"
regexNome = r"^[A-Za-zÀ-ÿ'\- ]+$"
regexEmail = r"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
regexPlaca = r'^[A-Z]{3}-\d{1}[A-Z]{1}\d{2}$'
regexData = r"^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])$"
regexHorario = r"^([01][0-9]|2[0-3]):([0-5][0-9])$"
regexIdCentro = r"^C\d{5}$"
regexHorarioFuncionamento = r"^(?:[01]\d|2[0-3]):[0-5]\d - (?:[01]\d|2[0-3]):[0-5]\d$"
regexIdCargo = r"^CG\d{4}$"
regexMatriculaFunc = r"^M\d{5}$"
regexIdPeca = r"^P\d{5}$"
regexIdServico = r"^SV\d{4}$"
regexDuracao = r'\b\d{1,2}(\.\d{1,2})?-\d{1,2}(\.\d{1,2})? horas\b'

# FUNÇÕES DO USUÁRIO
# cadastrar usuario
def cadastro_usuario():
    print("Iniciando cadastro do usuário...\n")
    usuario = {}
    # cadastro nome
    while True:
        try:
            nome = input("Digite o nome..................................: ")
            if re.match(regexNome, nome) is None:
                raise ValueError("Digite um nome válido.")     
        except ValueError as e:
            print(e)  
        else:
            usuario['nome'] = nome
            print('Nome registrado com sucesso.')
            break
    # cadastro email
    while True:
        try:
            email = input("Digite o email.................................: ")
            if re.match(regexEmail, email) is None:
                raise ValueError("Digite um email válido.")
            email_repetido = any(usuario['email'] == email for usuario in usuarios)
            if email_repetido:
                raise ValueError("O Email inserido já está sendo utilizado.")  
        except ValueError as e:
            print(e)
        else:
            usuario['email'] = email
            print('Email registrado com sucesso.')
            break
    # cadastro senha
    while True:
        try:
            senha = input("Digite uma nova senha..........................: ")
            if len(senha) > 30 or len(senha) < 1:
                raise ValueError("Sua senha deve conter de 1 a 30 caracteres.")
        except ValueError as e:
            print(e)
        else:
            usuario['senha'] = senha
            print("Senha registrada com sucesso.")
            break
    # cadastro CPF
    while True:
        try:
            cpf = input("Digite o CPF (ex: xxx.xxx.xxx-xx)..............: ")
            if re.match(regexCpf, cpf) is None:
                raise ValueError("Digite um CPF válido.")
            cpf_repetido = any(user['cpf'] == cpf for user in usuarios)
            if cpf_repetido:
                raise ValueError("O CPF inserido já está sendo utilizado.")
        except ValueError as e:
            print(e) 
        else:
            usuario['cpf'] = cpf
            print('CPF registrado com sucesso.')
            break
    # cadastro telefone
    while True:
        try:
            telefone = input("Digite o número de telefone (ex: xx xxxxx-xxxx): ")
            if re.match(regexTel, telefone) is None:
                raise ValueError("Digite um número de telefone válido.")
        except ValueError as e:
            print(e)
        else:
            usuario['telefone'] = telefone
            print("Telefone registrado com sucesso.")
            break
    usuarios.append(usuario)
    print("\nUsuário cadastrado com sucesso!")

# visualizar um usuário
def read_usuario(cpf):
    try:
        usuario_atual = verificar_usuario(cpf)
    except ValueError as e:
        print(e)
    else:
        print("\n==============[ INFORMAÇÕES DO USUÁRIO ]==============\n") 
        print(f"Nome......: {usuario_atual['nome']}") 
        print(f"Email.....: {usuario_atual['email']}") 
        print(f"Senha.....: {usuario_atual['senha']}") 
        print(f"CPF.......: {usuario_atual['cpf']}") 
        print(f"Telefone..: {usuario_atual['telefone']}\n") 
        input("Pressione ENTER para voltar ao menu: ")
    finally:
        print("\nRetornando ao menu do usuário...")

# deleta um usuario a partir do CPF
def deletar_usuario(cpf):
    try:
        usuario = verificar_usuario(cpf)
    except ValueError as e:
        print(e)
    else:
        while True:
            op_delete = input(f"\nDeseja realmente deletar o usuário de CPF {cpf}? (os veículos, agendamentos e diagnósticos relacionados também serão removidos) S ou N: ")
            if op_delete.upper() != "S" and op_delete.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_delete.upper() == "S":
                usuarios.remove(usuario)
                for veiculo in veiculos:
                    if veiculo['cpf_proprietario'] == cpf: #remove o veiculo
                        deletar_veiculo(veiculo['placa'], True)       
                for agendamento in agendamentos:
                    if agendamento['cpf_usuario'] == cpf: # deleta o agendamento feito pelo usuario deletado
                        deletar_agendamento(agendamento['agendamento_id'], True)
                # falta remover o diagnostico/orcamento qnd deletado
                print("\nUsuário removido com sucesso.")
                break
            elif op_delete.upper() == "N":
                print("\nUsuário não foi removido.")
                break
    finally:
        print('\nRetornando ao menu de usuário...')
        
# atualiza um usuario
def atualizar_usuario(cpf):
    try:
        usuario_atual = verificar_usuario(cpf)
    except ValueError as e:
        print(e)
    else:
        while True:
            print("\n==============[ ATUALIZAÇÃO DOS DADOS DO USUÁRIO ]==============\n")
            print("1 - Atualizar Nome")
            print("2 - Atualizar Email")
            print("3 - Atualizar CPF")
            print("4 - Atualizar Senha")
            print("5 - Atualizar Telefone")
            print("0 - Sair")
            op_atualizar = input("\nSelecione uma opção: ")
            if not op_atualizar.isdigit() or int(op_atualizar) > 5 or int(op_atualizar) < 0:
                print("\nSelecione uma opção válida.")
                continue
            op_atualizar = int(op_atualizar)
            if op_atualizar == 0:
                break
            match op_atualizar:
                case 1:
                    while True:
                        try:
                            nome = input("Digite o novo nome............................: ")
                            if re.match(regexNome, nome) is None:
                                raise ValueError("Digite um nome válido.")
                        except ValueError as e:
                            print(e)
                        else:
                            usuario_atual['nome'] = nome
                            print('\nNome atualizado com sucesso.')
                            break
                case 2:
                    while True:
                        try:
                            email = input("Digite o novo email..........................: ")
                            if re.match(regexEmail, email) is None:
                                raise ValueError("Digite um email válido.")
                            email_repetido = any(usuario['email'] == email for usuario in usuarios)
                            if email_repetido:
                                raise ValueError("O Email inserido já está sendo utilizado.")
                        except ValueError as e:
                            print(e)
                        else:
                            usuario_atual['email'] = email
                            print('\nEmail atualizado com sucesso.')
                            break
                case 3:
                    while True:
                        try:
                            cpf = input("Digite o novo CPF (ex: xxx.xxx.xxx-xx)........: ")
                            if re.match(regexCpf, cpf) is None:
                                raise ValueError("Digite um CPF válido.")
                            cpf_repetido = any(user['cpf'] == cpf for user in usuarios)
                            if cpf_repetido:
                                raise ValueError("O CPF inserido já está sendo utilizado.")
                        except ValueError as e:
                            print(e)
                        else:
                            for veiculo in veiculos:
                                if veiculo['cpf_proprietario'] == usuario_atual['cpf']: # atualiza o cpf nos veiculos
                                    veiculo['cpf_proprietario'] = cpf
                            for agendamento in agendamentos:
                                if agendamento['cpf_usuario'] == usuario_atual['cpf']:
                                    agendamento['cpf_usuario'] = cpf
                            usuario_atual['cpf'] = cpf
                            print('\nCPF atualizado com sucesso.')
                            break
                case 4:
                    while True:
                        try:
                            senha = input("Digite uma nova senha......................: ")
                            if len(senha) > 30 or len(senha) < 1:
                                raise ValueError("Sua senha deve conter de 1 a 30 caracteres.")
                        except ValueError as e:
                            print(e)
                        else:
                            usuario_atual['senha'] = senha
                            print("\nSenha atualizada com sucesso.")
                            break
                case 5:
                     while True:
                        try:
                            telefone = input("Digite o novo número de telefone (ex: xx xxxxx-xxxx): ")
                            if re.match(regexTel, telefone) is None:
                                raise ValueError("Digite um número de telefone válido.")
                        except ValueError as e:
                            print(e)
                        else:
                            usuario_atual['telefone'] = telefone
                            print("\nTelefone atualizado com sucesso.")
                            break
    finally:
        print('\nRetornando ao menu de usuário...')

# verifica a existencia do usuario e retorna o usuario
def verificar_usuario(cpf):
    if re.match(regexCpf, cpf) is None:
        raise ValueError("Digite um CPF válido.")
    usuario_atual = next((usuario for usuario in usuarios if usuario['cpf'] == cpf), None)
    if usuario_atual is None:
        raise ValueError("\nO CPF informado não foi cadastrado.")
    elif usuario_atual: 
        return usuario_atual

# gerenciamento usuario
def gerenciar_usuario():
        print("\nIniciando menu de gerenciamento do usuário...") 
        while True:
            print("\n==============[ GERENCIAMENTO DE USUÁRIOS ]==============\n")
            print("1 - Cadastrar Usuário")
            print("2 - Visualizar Usuário")
            print("3 - Atualizar Usuário")
            print("4 - Deletar Usuário")
            print("0 - Sair")
            verif_usuario_op = input("\nSelecione uma opção: ")
            if not verif_usuario_op.isdigit() or int(verif_usuario_op) > 4 or int(verif_usuario_op) < 0:
                print("\nSelecione uma opção válida.")
                continue
            verif_usuario_op = int(verif_usuario_op)
            if verif_usuario_op == 0:
                break
            elif verif_usuario_op == 1:
                cadastro_usuario()
            elif verif_usuario_op == 2:
                input_cpf = input("Digite o CPF do Usuário que deseja visualizar: ")
                read_usuario(input_cpf)
            elif verif_usuario_op == 3:
                input_cpf = input("Digite o CPF do Usuário que deseja atualizar.: ")
                atualizar_usuario(input_cpf)
            elif verif_usuario_op == 4:
                input_cpf = input("Digite o CPF do Usuário que deseja deletar...: ")
                deletar_usuario(input_cpf)

# FUNÇÕES DO VEÍCULO
#cadastro do veiculo
def cadastro_veiculo():
    print("Iniciando cadastro de veículo...\n")
    veiculo = {}
    # cadastro marca através de um menu
    while True:
        try:
            print("==============[ MARCA ]==============\n")
            for i in range(len(marcas)):
                print(f"{i:<2} - {list(marcas)[i]}")
            marca = input("\nSelecione a marca do carro...........: ")
            if not marca.isdigit() or (int(marca) >= 10 or int(marca) < 0):
                raise ValueError("\nSelecione uma opção válida.\n")
        except ValueError as e:
            print(e)
        else:
            marca = int(marca)
            veiculo['marca'] = list(marcas.keys())[marca]
            print('Marca registrada com sucesso.')
            break
    # cadastro modelo através de um menu
    while True:
        try:
            print("\n==============[ MODELO ]==============\n")
            for i in range(len(marcas[veiculo['marca']])):
                print(f"{i} - {list(marcas[veiculo['marca']])[i]}")
            modelo = input("\nSelecione o modelo do carro..........: ")
            if not modelo.isdigit() or (int(modelo) > 2 or int(modelo) < 0):
                raise ValueError("\nSelecione uma opção válida.")
        except ValueError as e:
            print(e)
        else:
            modelo = int(modelo)
            veiculo['modelo'] = list(marcas[veiculo['marca']].keys())[modelo]
            print('Modelo registrado com sucesso.')
            break
    # cadastro do ano do veículo
    while True:
        try:
            print("\n==============[ ANO ]==============\n")
            for i in range(len(marcas[veiculo['marca']][veiculo['modelo']])):
                print(f"{i} - {list(marcas[veiculo['marca']][veiculo['modelo']])[i]}")
            ano = input("\nSelecione o ano do carro.............: ")
            if not ano.isdigit() or (int(ano) > 2 or int(ano) < 0):
                raise ValueError("\nSelecione uma opção válida.")
        except ValueError as e:
            print(e)
        else:
            ano = int(ano)
            veiculo['ano'] = marcas[veiculo['marca']][veiculo['modelo']][ano]
            print('Ano registrado com sucesso.')
            break
    # cadastro da placa do veículo
    while True:
        try:
            placaVeiculo = input("Qual a placa do carro? (ex: ABC-1D23): ")
            if re.match(regexPlaca, placaVeiculo) is None:
                raise ValueError("Insira um formato de placa válido.")
            placa_repetida = any(veiculo['placa'] == placaVeiculo for veiculo in veiculos)
            if placa_repetida:
                raise ValueError("A placa informada já está cadastrada em outro veículo.")
        except ValueError as e:
            print(e)
        else:
            veiculo['placa'] = placaVeiculo
            print('Placa registrada com sucesso.')
            break
    # cadastro da quilometragem
    while True:
        try:
            quilometragem = float(input("Qual a quilometragem do veículo?.....: "))
            if quilometragem < 0:
                raise ValueError("Digite um valor maior que zero.")                
        except ValueError:
            print("Digite um valor numérico válido para a quilometragem.")
        else:
            print("Quilometragem registrada com sucesso.")
            veiculo['quilometragem'] = quilometragem
            break
    # cadastro do proprietario do veiculo
    while True:
        try: 
            cpf_usuario = input("Qual o CPF do proprietário do veículo? (ex: xxx.xxx.xxx-xx): ")
            if re.match(regexCpf, cpf_usuario) is None:
                raise ValueError("Digite um CPF válido.")
            cpf_existe = any(user['cpf'] == cpf_usuario for user in usuarios)
            if cpf_existe == False:
                raise ValueError("Nenhum usuário cadastrado com o CPF informado.")
        except ValueError as e:
            print(e)
        else:
            print("CPF do Proprietário registrado com sucesso.")
            veiculo['cpf_proprietario'] = cpf_usuario
            break
    veiculos.append(veiculo)
    print("\nVeículo foi cadastrado com sucesso!")

# visualizar veiculo
def read_veiculo(placa):
    try:
        veiculo_atual = verificar_veiculo(placa)
    except ValueError as e:
        print(e)
    else:
        print("\n==============[ INFORMAÇÕES DO VEÍCULO ]==============\n") 
        print(f"Marca......................: {veiculo_atual['marca'].capitalize()}") 
        print(f"Modelo.....................: {veiculo_atual['modelo']}") 
        print(f"Ano........................: {veiculo_atual['ano']}") 
        print(f"Placa......................: {veiculo_atual['placa']}")
        print(f"Quilometragem..............: {veiculo_atual['quilometragem']}km")
        print(f"CPF do usuário proprietário: {veiculo_atual['cpf_proprietario']}\n")
        input("Pressione ENTER para voltar ao menu: ")  
    finally:
        print("\nRetornando ao menu do veículo...")

# atualizar veiculo
def atualizar_veiculo(placa):
    try:
        veiculo_atual = verificar_veiculo(placa)        
    except ValueError as e:
        print(e)
    else:
        while True:
            print("\n==============[ ATUALIZAÇÃO DOS DADOS DO VEÍCULO ]==============\n")
            print("1 - Atualizar Marca, Modelo e Ano")
            print("2 - Atualizar Placa")
            print("3 - Atualizar Quilometragem")
            print("4 - Atualizar CPF do Proprietário")
            print("0 - Sair")
            op_atualizar = input("\nSelecione uma opção: ")
            if not op_atualizar.isdigit() or int(op_atualizar) > 4 or int(op_atualizar) < 0:
                print("\nSelecione uma opção válida.")
                continue
            op_atualizar = int(op_atualizar)
            if op_atualizar == 0:
                break
            match op_atualizar:
                case 1:
                    # atualizar marca, modelo e ano
                    while True:
                        try:
                            print("==============[ MARCA ]==============\n")
                            for i in range(len(marcas)):
                                print(f"{i:<2} - {list(marcas)[i]}")
                            marca = input("\nSelecione a nova marca do carro...........: ")
                            if not marca.isdigit() or (int(marca) >= 10 or int(marca) < 0):
                                raise ValueError("\nSelecione uma opção válida.\n")
                        except ValueError as e:
                            print(e)
                        else:
                            marca = int(marca)
                            veiculo_atual['marca'] = list(marcas.keys())[marca]
                            print('Marca atualizada com sucesso.')
                            break
                    # cadastro modelo através de um menu
                    while True:
                        try:
                            print("\n==============[ MODELO ]==============\n")
                            for i in range(len(marcas[veiculo_atual['marca']])):
                                print(f"{i} - {list(marcas[veiculo_atual['marca']])[i]}")
                            modelo = input("\nSelecione o novo modelo do carro..........: ")
                            if not modelo.isdigit() or (int(modelo) > 2 or int(modelo) < 0):
                                raise ValueError("\nSelecione uma opção válida.")
                        except ValueError as e:
                            print(e)
                        else:
                            modelo = int(modelo)
                            veiculo_atual['modelo'] = list(marcas[veiculo_atual['marca']].keys())[modelo]
                            print('Modelo atualizado com sucesso.')
                            break  
                    while True:
                        try:
                            print("\n==============[ ANO ]==============\n")
                            for i in range(len(marcas[veiculo_atual['marca']][veiculo_atual['modelo']])):
                                print(f"{i} - {list(marcas[veiculo_atual['marca']][veiculo_atual['modelo']])[i]}")
                            ano = input("\nSelecione o novo ano do carro.............: ")
                            if not ano.isdigit() or (int(ano) > 2 or int(ano) < 0):
                                raise ValueError("\nSelecione uma opção válida.")
                        except ValueError as e:
                            print(e)
                        else:
                            ano = int(ano)
                            veiculo_atual['ano'] = marcas[veiculo_atual['marca']][veiculo_atual['modelo']][ano]
                            print("Ano atualizado com sucesso.")
                            break
                    print('\nMarca, Modelo e Ano atualizados com sucesso!')      
                case 2:
                    # atualizar placa
                    while True:
                        try:
                            placaVeiculo = input("Qual a nova placa do carro? (ex: ABC-1D23): ")
                            if re.match(regexPlaca, placaVeiculo) is None:
                                raise ValueError("\nInsira um formato de placa válido.")
                            placa_repetida = any(veiculo['placa'] == placaVeiculo for veiculo in veiculos)
                            if placa_repetida:
                                raise ValueError("\nA placa informada já está cadastrada em outro veículo.")
                        except ValueError as e:
                            print(e)
                        else:
                            for agendamento in agendamentos: # quando a placa eh atualizada, atualiza também no agendamento
                                if agendamento['placa'] == veiculo_atual['placa']:
                                    agendamento['placa'] = placaVeiculo
                            for diagnostico in diagnosticos: # quando a placa eh atualizada, também atualiza o diagnóstico.
                                if diagnostico['placa_veiculo'] == veiculo_atual['placa']:
                                    diagnostico['placa_veiculo'] = placaVeiculo
                            veiculo_atual['placa'] = placaVeiculo
                            print('\nPlaca atualizada com sucesso!')
                            break
                case 3:
                    # atualizar quilometragem
                    while True:
                        try:
                            quilometragem = float(input("Qual a nova quilometragem do veículo?: "))
                            if quilometragem < 0:
                                raise ValueError("Digite um valor maior que zero.")
                        except ValueError:
                            print("Digite um valor numérico válido para a quilometragem.")
                        else: 
                            veiculo_atual['quilometragem'] = quilometragem
                            print('\nQuilometragem atualizada com sucesso!')
                            break
                case 4:
                    # atualizar cpf do proprietario
                    while True:
                        try: 
                            cpf_usuario = input("Qual o novo CPF do proprietário do veículo? (ex: xxx.xxx.xxx-xx): ")
                            if re.match(regexCpf, cpf_usuario) is None:
                                raise ValueError("\nDigite um CPF válido.")
                            cpf_existe = any(user['cpf'] == cpf_usuario for user in usuarios)
                            if cpf_existe == False:
                                raise ValueError("\nNenhum usuário cadastrado com o CPF informado.")
                        except ValueError as e:
                            print(e)
                            break
                        else:
                            for agendamento in agendamentos: # quando o cpf do proprietário é atualizado, atualiza no agendamento
                                if agendamento['cpf_usuario'] == veiculo_atual['cpf_proprietario']:
                                    agendamento['cpf_usuario'] = cpf_usuario
                            veiculo_atual['cpf_proprietario'] = cpf_usuario
                            print("\nCPF do proprietário atualizado com sucesso!")  
                            break
    finally:
        print("\nRetornando ao menu de veículo...")

#remover veiculo
def deletar_veiculo(placa, type):
    try:
        veiculo_atual = verificar_veiculo(placa)
    except ValueError as e:
        print(e)
        print('\nRetornando ao menu de veículo...')
    else:
        while True:
            if type == True:
                veiculos.remove(veiculo_atual)
                for agendamento in agendamentos:
                    if agendamento['placa'] == placa: #remove o agendamento, para que nao reste um agendamento 'orfao'
                        deletar_agendamento(agendamento['agendamento_id'], True)
                for diagnostico in diagnosticos:
                    if diagnostico['placa_veiculo'] == placa: # remove o diagnostico, para que não reste um diagnóstico 'orfao'
                        deletar_diagnostico(diagnostico['id_diagnostico'], True)
                break
            op_delete = input(f"\nDeseja realmente deletar o veículo de placa {placa}? (os agendamentos e diagnósticos relacionados também serão removidos) S ou N: ")
            if op_delete.upper() != "S" and op_delete.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_delete.upper() == "S":
                veiculos.remove(veiculo_atual)
                for agendamento in agendamentos:
                    if agendamento['placa'] == placa: #remove o agendamento, para que nao reste um agendamento 'orfao'
                        deletar_agendamento(agendamento['agendamento_id'], True)
                for diagnostico in diagnosticos:
                    if diagnostico['placa_veiculo'] == placa: # remove o diagnostico, para que não reste um diagnóstico 'orfao'
                        deletar_diagnostico(diagnostico['id_diagnostico'], True)
                #falta do diagnostico e orcamento
                print("\nVeículo removido com sucesso.")
                print('\nRetornando ao menu de veículo...')
                break
            elif op_delete.upper() == "N":
                print("\nVeículo não foi removido.")
                break
        
# verifica a existencia do veiculo e retorna o veiculo
def verificar_veiculo(placa):
        if re.match(regexPlaca, placa) is None:
            raise ValueError("Insira um formato de placa válido.")
        veiculo_atual = next((veiculo for veiculo in veiculos if veiculo['placa'] == placa), None)
        if veiculo_atual is None:
            raise ValueError("\nA placa informada não foi cadastrada.")
        elif veiculo_atual:
            return veiculo_atual

# gerenciamento veiculo
def gerenciar_veiculo():
    print("\nIniciando menu de gerenciamento do veículo...")
    while True:
        print("\n==============[ GERENCIAMENTO DE VEÍCULOS ]==============\n")
        print("1 - Cadastrar Veículo")
        print("2 - Visualizar Veículo")
        print("3 - Atualizar Veículo")
        print("4 - Deletar Veículo")
        print("0 - Sair")
        verif_veic_op = input("\nSelecione uma opção: ")
        if not verif_veic_op.isdigit() or int(verif_veic_op) > 4 or int(verif_veic_op) < 0:
            print("\nSelecione uma opção válida.")
            continue
        verif_veic_op = int(verif_veic_op)
        if verif_veic_op == 0:
            break
        elif verif_veic_op == 1:
            if usuarios == []:
                print("\nNenhum usuário cadastrado no sistema. Impossível cadastrar novo veículo.")
            else:
                cadastro_veiculo()
        elif verif_veic_op == 2:
            placa_input = input("Qual a placa do veículo que deseja visualizar?: ")
            read_veiculo(placa_input)
        elif verif_veic_op == 3:
            placa_input = input("Qual a placa do veículo que deseja atualizar?: ")
            atualizar_veiculo(placa_input)
        elif verif_veic_op == 4:
            placa_input = input("Qual a placa do veículo que deseja remover?: ")
            deletar_veiculo(placa_input, False)

# FUNÇÕES DO AGENDAMENTO
# agenda um serviço 
def agendar_servico(placa):
    print("\nIniciando agendamento do serviço...")
    agendamento = {}
    try:
        veiculo = verificar_veiculo(placa)
    except ValueError as e:
            print(e)
    else:
        # ID do agendamento criado com uuid
        agendamento['agendamento_id'] = str(uuid.uuid4())
        # adicionando servico   
        while True:
            try:
                print("\n==============[ SERVIÇOS ]==============\n")
                for i in range(len(list(servicos))):
                    print(f"{i} - {servicos[i]['tipo_servico']}")
                op_servico = input("\nQual serviço será realizado?: ")
                if not op_servico.isdigit() or int(op_servico) > (len(list(servicos)) - 1) or int(op_servico) < 0:
                    raise ValueError("\nSelecione uma opção válida.")
            except ValueError as e:
                print(e)
            else: 
                op_servico = int(op_servico)
                agendamento['id_servico'] = servicos[op_servico]['id_servico']
                print('Serviço registrado com sucesso.')
                break
        # adicionando centro automotivo
        while True:
            try:
                print("\n==============[ CENTROS AUTOMOTIVOS ]==============\n")
                for i in range(len(list(centros))):
                    print(f"{i} - {list(centros)[i]['nome_centro']}")
                op_centro = input("\nEm qual centro automotivo o serviço será realizado?......: ")
                if not op_centro.isdigit() or int(op_centro) > (len(list(centros)) - 1) or int(op_centro) < 0:
                    raise ValueError("\nSelecione uma opção válida.")
            except ValueError as e:
                print(e)
            else: 
                op_centro = int(op_centro)
                agendamento['id_centro'] = centros[op_centro]['id_centro']
                print('Centro Automotivo registrado com sucesso.')
                break
        # adicionando data
        while True:
            try:
                data = input("Qual a data que o serviço será realizado? (ex: 16/09)....: ")
                if re.match(regexData, data) is None:
                    raise ValueError("Digite uma data válida.")
            except ValueError as e:
                print(e) 
            else:
                agendamento['data'] = data
                print("Data registrada com sucesso.")
                break
        # adicionando horario
        while True:
            try:
                horario = input("Qual o horário que o serviço será realizado?: (ex: 13:30): ")
                if re.match(regexHorario, horario) is None:
                    raise ValueError("Digite um horário válido.")
            except ValueError as e:
                print(e)  
            else:
                agendamento['horario'] = horario
                print('Horário registrado com sucesso.')
                break 
        # adicionando placa
        agendamento['placa'] = veiculo['placa']
        # adicionando cpf do proprietario
        agendamento['cpf_usuario'] = veiculo['cpf_proprietario']
        agendamentos.append(agendamento)
        print(f"\nAgendamento criado com sucesso! O ID do agendamento é {agendamento['agendamento_id']}")
    finally:
        print('\nRetornando ao menu de agendamento...')

# visualizar informações de um agendamento pelo id
def read_agendamento(id):
    try:
        agendamento_atual = verificar_agendamento(id)
    except ValueError as e:
        print(e)
    else:
        print("\n==============[ INFORMAÇÕES DO AGENDAMENTO ]==============\n") 
        print(f"ID do Agendamento............: {agendamento_atual['agendamento_id']}")
        print(f"ID do Serviço................: {agendamento_atual['id_servico']}")
        print(f"Serviço......................: {next((servico['tipo_servico'] for servico in servicos if servico['id_servico'] == agendamento_atual['id_servico']), None)}") 
        print(f"Preço........................: R${next((servico['preco'] for servico in servicos if servico['id_servico'] == agendamento_atual['id_servico']), None)}")
        print(f"ID do Centro Automotivo......: {agendamento_atual['id_centro']}")
        print(f"Centro Automotivo............: {next((centro['nome_centro'] for centro in centros if centro['id_centro'] == agendamento_atual['id_centro']), None)}") 
        print(f"Data do Agendamento..........: {agendamento_atual['data']}") 
        print(f"Horário......................: {agendamento_atual['horario']}")
        print(f"Placa do Veículo.............: {agendamento_atual['placa']}")
        print(f"CPF do usuário...............: {agendamento_atual['cpf_usuario']}")
        input("\nPressione ENTER para voltar ao menu: ") 
    finally:
        print("\nRetornando ao menu de agendamento...")

# atualizar o agendamento
def atualizar_agendamento(id):
    try:
        agendamento_atual = verificar_agendamento(id)
    except ValueError as e:
        print(e)
    else:
        while True:
            print("\n==============[ ATUALIZAÇÃO DOS DADOS DO AGENDAMENTO ]==============\n")
            print("1 - Atualizar ID do agendamento")
            print("2 - Atualizar Serviço")
            print("3 - Atualizar Centro Automotivo")
            print("4 - Atualizar Data do agendamento")
            print("5 - Atualizar Horário do agendamento")
            print("6 - Atualizar Veículo")
            print("0 - Sair")
            op_atualizar = input("\nSelecione uma opção: ")
            if not op_atualizar.isdigit() or int(op_atualizar) > 6 or int(op_atualizar) < 0:
                print("\nSelecione uma opção válida.")
                continue
            op_atualizar = int(op_atualizar)
            if op_atualizar == 0:
                break
            match op_atualizar:
                case 1:
                    while True:
                        op_atualizar_id = input(f"\nDeseja realmente atualizar o ID do agendamento? S ou N: ")
                        if op_atualizar_id.upper() != "S" and op_atualizar_id.upper() != "N":
                            print("\nDigite uma opção válida.")
                            continue
                        elif op_atualizar_id.upper() == "S":
                            agendamento_atual['agendamento_id'] = str(uuid.uuid4())
                            print(f"\nID do agendamento atualizado com sucesso! O novo id é: {agendamento_atual['agendamento_id']}")
                            break
                        elif op_atualizar_id.upper() == "N":
                            print("\nO ID do agendamento não foi atualizado.")
                            break
                case 2:
                    while True:
                        try:
                            print("\n==============[ SERVIÇOS ]==============\n")
                            for i in range(len(list(servicos))):
                                print(f"{i} - {servicos[i]['tipo_servico']}")
                            op_servico = input("\nQual novo serviço será realizado?: ")
                            if not op_servico.isdigit() or int(op_servico) > (len(list(servicos)) - 1) or int(op_servico) < 0:
                                raise ValueError("\nSelecione uma opção válida.")
                        except ValueError as e:
                            print(e)
                        else: 
                            op_servico = int(op_servico)
                            agendamento_atual['id_servico'] = servicos[op_servico]['id_servico']
                            print('Serviço atualizado com sucesso.')
                            break
                case 3:
                    while True:
                        try:
                            print("\n==============[ CENTROS AUTOMOTIVOS ]==============\n")
                            for i in range(len(list(centros))):
                                print(f"{i} - {list(centros)[i]['nome_centro']}")
                            op_centro = input("\nQual o novo centro em que o serviço será realizado?: ")
                            if not op_centro.isdigit() or int(op_centro) > (len(list(centros)) - 1) or int(op_centro) < 0:
                                raise ValueError("\nSelecione uma opção válida.")
                        except ValueError as e:
                            print(e)
                        else: 
                            op_centro = int(op_centro)
                            agendamento_atual['id_centro'] = centros[op_centro]['id_centro']
                            print("\nCentro Automotivo atualizado com sucesso!")
                            break
                case 4:
                    while True:
                        try:
                            data = input("Qual a nova data que o serviço será realizado?: (ex: 16/09): ")
                            if re.match(regexData, data) is None:
                                raise ValueError("Digite uma data válida.")      
                        except ValueError as e:
                            print(e) 
                        else:
                            agendamento_atual['data'] = data
                            print("\nData atualizada com sucesso!")
                            break
                case 5:
                    while True:
                        try:
                            horario = input("Qual o novo horário que o serviço será realizado?: (ex: 13:30): ")
                            if re.match(regexHorario, horario) is None:
                                raise ValueError("Digite um horário válido.")
                        except ValueError as e:
                            print(e) 
                        else:
                            agendamento_atual['horario'] = horario
                            print("\nHorário atualizado com sucesso!")
                            break
                case 6:
                    while True:
                        try:
                            placa = input("Qual a placa do novo veículo?: ")
                            veiculo_existe = verificar_veiculo(placa)
                        except ValueError as e:
                            print(e)
                        else:
                            agendamento_atual['placa'] = veiculo_existe['placa']
                            for veiculo in veiculos:
                                if veiculo['placa'] == placa:
                                    agendamento_atual['cpf_usuario'] = veiculo['cpf_proprietario']
                            print("\nVeículo atualizado com sucesso!")
                            break
    finally:
        print("\nRetornando ao menu de agendamento...")

# deletar o agendamento feito
def deletar_agendamento(id, type):
    try: 
        agendamento_atual = verificar_agendamento(id)
    except ValueError as e:
        print(e)
        print("\nRetornando ao menu de agendamento...")
    else:
        while True:
            if type == True: # caso o agendamento esteja sendo deletado por causa da remoção do usuário.
                agendamentos.remove(agendamento_atual)
                break
            op_delete = input(f"\nDeseja realmente remover o agendamento de id {id}? S ou N: ")
            if op_delete.upper() != "S" and op_delete.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_delete.upper() == "S":
                agendamentos.remove(agendamento_atual)
                print("\nAgendamento removido com sucesso.")
                print("\nRetornando ao menu de agendamento...")
                break
            elif op_delete.upper() == "N":
                print("\nO agendamento não foi removido.")
                break
        
# verifica a existencia do agendamento, retornando o agendamento
def verificar_agendamento(id):
    agendamento_atual = next((agendamento for agendamento in agendamentos if agendamento['agendamento_id'] == id), None)
    if agendamento_atual is None:
        raise ValueError("\nO ID informado não pertence a um agendamento ativo.")
    else:
        return agendamento_atual

# gerenciar o serviço agendado
def gerenciar_agendamento():
    print("\nIniciando menu de gerenciamento do serviço...") 
    while True:
        print("\n==============[ GERENCIAMENTO AGENDAMENTO DE SERVIÇO ]==============\n")
        print("1 - Criar agendamento de serviço")
        print("2 - Visualizar informações do agendamento")
        print("3 - Atualizar agendamento")
        print("4 - Deletar agendamento")
        print("0 - Sair")
        verif_gerenc_op = input("\nSelecione uma opção: ")
        if not verif_gerenc_op.isdigit() or int(verif_gerenc_op) > 4 or int(verif_gerenc_op) < 0:
            print("\nSelecione uma opção válida.")
            continue
        verif_gerenc_op = int(verif_gerenc_op)
        if verif_gerenc_op == 0:
            break
        elif verif_gerenc_op == 1:
            placa_agendamento = input("Qual a placa do veículo para o agendamento?: ")
            agendar_servico(placa_agendamento)
        elif verif_gerenc_op == 2:
            id_agendamento = input("Qual o ID do agendamento que deseja visualizar?: ")
            read_agendamento(id_agendamento)
        elif verif_gerenc_op == 3:
            id_agendamento = input("Qual o ID do agendamento que deseja atualizar?: ")
            atualizar_agendamento(id_agendamento)
        elif verif_gerenc_op == 4:
            id_agendamento = input("Qual o ID do agendamento que deseja deletar?: ")
            deletar_agendamento(id_agendamento, False)

# FUNÇÕES DO CENTRO AUTOMOTIVO
# gerenciador de centro
def gerenciar_centro():
    print("\nIniciando menu de gerenciamento de Centro Automotivo...") 
    while True:
        print("\n==============[ GERENCIAMENTO CENTRO AUTOMOTIVO ]==============\n")
        print("1 - Criar Centro Automotivo")
        print("2 - Visualizar informações do Centro Automotivo")
        print("3 - Atualizar Centro Automotivo")
        print("4 - Deletar Centro Automotivo")
        print("5 - Visualizar todos os Centros Aautomotivos")
        print("0 - Sair")
        verif_gerenc_op = input("\nSelecione uma opção: ")
        if not verif_gerenc_op.isdigit() or int(verif_gerenc_op) > 5 or int(verif_gerenc_op) < 0:
            print("\nSelecione uma opção válida.")
            continue
        verif_gerenc_op = int(verif_gerenc_op)
        if verif_gerenc_op == 0:
            break
        elif verif_gerenc_op == 1:
            cadastrar_centro()
        elif verif_gerenc_op == 2:
            id_centro = input("Qual o ID do centro automotivo que deseja visualizar?: ")
            read_centro(id_centro)
        elif verif_gerenc_op == 3:
            id_centro = input("Qual o ID do centro automotivo que deseja atualizar?: ")
            atualizar_centro(id_centro)
        elif verif_gerenc_op == 4:
            id_centro = input("Qual o ID do centro automotivo que deseja deletar?: ")
            deletar_centro(id_centro)
        elif verif_gerenc_op == 5:
            visualizar_centros()

# cadastra um centro automotivo
def cadastrar_centro():
    print("\nIniciando cadastro de centro automotivo...\n")
    centro = {}
    # cadastro id do centro
    while True:
        try:
            id_centro = input("Digite um ID para o Centro Automotivo (formato: CXXXXX. Ex: C23213): ")
            if re.match(regexIdCentro, id_centro) is None:
                raise ValueError("Digite um ID com formato válido.")
            id_repetido = any(centro['id_centro'] == id_centro for centro in centros)
            if id_repetido:
                raise ValueError("O ID digitado já existe.")
        except ValueError as e:
            print(e)
        else: 
            centro['id_centro'] = id_centro
            print('ID registrado com sucesso.')
            break
    # cadastro nome centro
    while True:
        try:
            nome_centro = input("Digite o nome do centro automotivo.................................: ")
            if re.match(regexNome, nome_centro) is None:
                raise ValueError("Digite um nome válido.")
        except ValueError as e:
            print(e)  
        else:
            centro['nome_centro'] = nome_centro
            print('Nome do centro registrado com sucesso.')
            break
    # cadastro endereço centro
    while True:
        try:
            endereco_centro = input("Digite o endereço do centro automotivo.............................: ")
            if endereco_centro == '':
                raise ValueError("Insira um endereço válido.")
        except ValueError as e:
            print(e)
        else:
            centro['endereco_centro'] = endereco_centro
            print('Endereço registrado com sucesso.')
            break
    # cadastro telefone centro
    while True:
        try:
            telefone_centro = input("Digite o telefone do centro automotivo (ex: (11) 3293-3923)........: ")
            if re.match(regexTel, telefone_centro) is None:
                raise ValueError("Insira um telefone válido.")
        except ValueError as e:
            print(e)
        else:
            centro['telefone_centro'] = telefone_centro
            print("Telefone registrado com sucesso.")
            break
    # cadastro horario funcionamento
    while True:
        try:
            horario_funcionamento = input("Digite o horário de funcionamento (ex: 08:00 - 17:30)..............: ")
            if re.match(regexHorarioFuncionamento, horario_funcionamento) is None:
                raise ValueError("Digite um horário válido.")
        except ValueError as e:
            print(e) 
        else: 
            centro['horario_funcionamento'] = horario_funcionamento
            print('Horário de funcionamento registrado com sucesso.')
            break
    centros.append(centro)
    print(f"\nCentro Automotivo de ID: {id_centro} cadastrado com sucesso!") 

# leitura de um centro automotivo
def read_centro(id_centro):
    try:
        centro_atual = verificar_centro(id_centro)
    except ValueError as e:
        print(e)
    else:
        print(f"\n==============[ INFORMAÇÕES DO CENTRO AUTOMOTIVO {centro_atual['nome_centro'].upper()} ]==============\n") 
        print(f"ID do Centro............: {centro_atual['id_centro']}")
        print(f"Nome do Centro..........: {centro_atual['nome_centro']}")
        print(f"Endereço do Centro......: {centro_atual['endereco_centro']}")
        print(f"Telefone do Centro......: {centro_atual['telefone_centro']}")
        print(f"Horário de Funcionamento: {centro_atual['horario_funcionamento']}")

# atualiza os dados de um centro automotivo
def atualizar_centro(id_centro):
    try:
        centro_atual = verificar_centro(id_centro)
    except ValueError as e:
        print(e)
    else:
        while True:
            print("\n==============[ ATUALIZAÇÃO DOS DADOS DO CENTRO AUTOMOTIVO ]==============\n")
            print("1 - Atualizar ID do Centro")
            print("2 - Atualizar Nome do Centro")
            print("3 - Atualizar Endereço do Centro")
            print("4 - Atualizar Telefone do Centro")
            print("5 - Atualizar Horário de funcionamento do Centro")
            print("0 - Sair")
            op_atualizar = input("\nSelecione uma opção: ")
            if not op_atualizar.isdigit() or int(op_atualizar) > 5 or int(op_atualizar) < 0:
                print("\nSelecione uma opção válida.")
                continue
            op_atualizar = int(op_atualizar)
            if op_atualizar == 0:
                print('Retornando ao menu de centro....')
                break
            match op_atualizar:
                case 1:
                    while True:
                        try:
                            id_centro = input("Digite um novo ID para o Centro Automotivo (formato: CXXXXX. Ex: C23213): ")
                            if re.match(regexIdCentro, id_centro) is None:
                                raise ValueError("Digite um ID com formato válido.")
                            id_repetido = any(centro['id_centro'] == id_centro for centro in centros)
                            if id_repetido:
                                raise ValueError("O ID digitado já existe.")
                        except ValueError as e:
                            print(e)
                        else:
                            for agendamento in agendamentos:
                                if agendamento['id_centro'] == centro_atual['id_centro']: # atualiza o id do centro no agendamento
                                    agendamento['id_centro'] = id_centro
                            for funcionario in funcionarios:
                                if funcionario['id_centro'] == centro_atual['id_centro']:
                                    funcionario['id_centro'] = id_centro
                            centro_atual['id_centro'] = id_centro
                            print('ID atualizado com sucesso.')
                            break
                case 2:
                    while True:
                        try:
                            nome_centro = input("Digite o novo nome do centro automotivo.................................: ")
                            if re.match(regexNome, nome_centro) is None:
                                raise ValueError("Digite um nome válido.")
                        except ValueError as e:
                            print(e)  
                        else:
                            centro_atual['nome_centro'] = nome_centro
                            print('Nome do centro atualizado com sucesso.')
                            break
                case 3:
                    while True:
                        try:
                            endereco_centro = input("Digite o novo endereço do centro automotivo.............................: ")
                            if endereco_centro == '':
                                raise ValueError("Digite um endereço válido.")
                        except ValueError as e:
                            print(e)
                        else:
                            centro_atual['endereco_centro'] = endereco_centro
                            print('Endereço atualizado com sucesso.')
                            break
                case 4:
                    while True:
                        try:
                            telefone_centro = input("Digite o novo telefone do centro automotivo (ex: (11) 3293-3923)........: ")
                            if re.match(regexTel, telefone_centro) is None:
                                raise ValueError("Insira um telefone válido.")
                        except ValueError as e:
                            print(e)
                        else:
                            centro_atual['telefone_centro'] = telefone_centro
                            print("Telefone atualizado com sucesso.")
                            break
                case 5:
                    while True:
                        try:
                            horario_funcionamento = input("Digite o novo horário de funcionamento (ex: 08:00 - 17:30)..............: ")
                            if re.match(regexHorarioFuncionamento, horario_funcionamento) is None:
                                raise ValueError("Digite um horário válido.")
                        except ValueError as e:
                            print(e) 
                        else:
                            centro_atual['horario_funcionamento'] = horario_funcionamento
                            print('Horário de funcionamento atualizado com sucesso.')
                            break
    finally:
        print("\nRetornando ao menu de centro automotivo...")

# deletar um centro automotivo
def deletar_centro(id_centro):
    try: 
        centro_atual = verificar_centro(id_centro)            
    except ValueError as e:
        print(e)
    else:
        while True:
            op_delete = input(f"\nDeseja realmente remover o centro {centro_atual['nome_centro']} de ID {id_centro}? S ou N: ")
            if op_delete.upper() != "S" and op_delete.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_delete.upper() == "S":
                centros.remove(centro_atual)
                for agendamento in agendamentos:
                    if agendamento['id_centro'] == id_centro: # remove o agendamento, já que se o centro não existe o agendamento deve ser refeito.
                        deletar_agendamento(agendamento['agendamento_id'], True)
                for funcionario in funcionarios:
                    if funcionario['id_centro'] == id_centro:
                        funcionario['id_centro'] = None
                print("\nCentro removido com sucesso.")
                break
            elif op_delete.upper() == "N":
                print("\nO centro não foi removido.")
                break
    finally:
        print("\nRetornando ao menu de centro automotivo...")

# verifica a existencia de um centro automotivo, retornando um
def verificar_centro(id_centro):
    if re.match(regexIdCentro, id_centro) is None:
        raise ValueError("Digite um ID com formato válido.")
    centro_atual = next((centro for centro in centros if centro['id_centro'] == id_centro), None)
    if centro_atual is None:
        raise ValueError("\nO ID informado não pertence a um centro automotivo cadastrado.")
    else:
        return centro_atual

# visualizar todos os centros
def visualizar_centros():
    while True:
        try:
            if centros == []:
                raise ValueError('Nenhum Centro Automotivo cadastrado.')
        except ValueError as e:
            print(e)
        else:
            print("\n==============[ LISTA DE CENTROS AUTOMOTIVOS ]==============")
            for centro in centros:
                read_centro(centro['id_centro'])
            input('\nPressione ENTER para retornar: ')
            break
        finally:
            print('\nRetornando ao menu de centros.....')

# FUNÇÕES DE CARGO
# gerenciador de cargo
def gerenciar_cargo():
    print("\nIniciando menu de gerenciamento de Cargo...") 
    while True:
        print("\n==============[ GERENCIAMENTO CARGO ]==============\n")
        print("1 - Criar Cargo")
        print("2 - Visualizar informações do Cargo")
        print("3 - Atualizar Cargo")
        print("4 - Deletar Cargo")
        print("5 - Visualizar todos os Cargos")
        print("0 - Sair")
        verif_gerenc_op = input("\nSelecione uma opção: ")
        if not verif_gerenc_op.isdigit() or int(verif_gerenc_op) > 5 or int(verif_gerenc_op) < 0:
            print("\nSelecione uma opção válida.")
            continue
        verif_gerenc_op = int(verif_gerenc_op)
        if verif_gerenc_op == 0:
            break
        elif verif_gerenc_op == 1:
            criar_cargo()
        elif verif_gerenc_op == 2:
            id_cargo = input("Qual o ID do cargo que deseja visualizar?: ")
            read_cargo(id_cargo)
        elif verif_gerenc_op == 3:
            id_cargo = input("Qual o ID do cargo que deseja atualizar?: ")
            atualizar_cargo(id_cargo)
        elif verif_gerenc_op == 4:
            id_cargo = input("Qual o ID do cargo que deseja deletar?: ")
            deletar_cargo(id_cargo)
        elif verif_gerenc_op == 5:
            visualizar_cargos()

# cria um cargo
def criar_cargo():
    print("Iniciando cadastro de cargo...\n")
    cargo = {}
    # cadastro id do cargo
    while True:
        try:
            id_cargo = input("Digite um ID para o Cargo (formato: CGXXXX. Ex: CG3213): ")
            if re.match(regexIdCargo, id_cargo) is None:
                raise ValueError("Digite um ID com formato válido.")
            id_repetido = any(cargo['id_cargo'] == id_cargo for cargo in cargos)
            if id_repetido:
                raise ValueError("O ID digitado já existe.")
        except ValueError as e:
            print(e)
        else:
            cargo['id_cargo'] = id_cargo
            print('ID registrado com sucesso.')
            break
    # cadastro nome do cargo
    while True:
        try:
            nome_cargo = input("Digite o nome do cargo.................................: ")
            if re.match(regexNome, nome_cargo) is None:
                raise ValueError("Digite um nome válido.")
        except ValueError as e:
            print(e) 
        else:
            cargo['nome_cargo'] = nome_cargo
            print('Nome do cargo registrado com sucesso.')
            break 
    # cadastro area cargo
    while True:
        try:
            area_cargo = input("Digite a área do cargo.................................: ")
            if re.match(regexNome, nome_cargo) is None:
                raise ValueError("Área do cargo inválida.")
        except ValueError as e:
            print(e)
        else:
            cargo['area_cargo'] = area_cargo
            print('Área registrada com sucesso.')
            break
    # cadastro descricao cargo
    while True:
        try:
            descricao_cargo = input("Digite a descrição do cargo............................: ")
            if descricao_cargo == '':
                raise ValueError("Descrição vazia.")
        except ValueError as e:
            print(e)
        else:
            cargo['descricao_cargo'] = descricao_cargo
            print("Descrição registrada com sucesso.")
            break
    # cadastro salario cargo
    while True:
        try:
            salario_cargo = float(input("Qual o salário do cargo?...............................: "))
            if salario_cargo < 0:
                raise ValueError("Digite um valor maior que zero.")        
        except ValueError:
            print("Digite um valor numérico válido para o salário.") 
        else:
            cargo['salario_cargo'] = salario_cargo
            print("Salário do Cargo registrado com sucesso.")
            break
    cargos.append(cargo)
    print(f"\nCargo de ID: {id_cargo} cadastrado com sucesso!") 

# visualiza um cargo
def read_cargo(id_cargo):
    try:
        cargo_atual = verificar_cargo(id_cargo)
    except ValueError as e:
        print(e)
    else:
        print(f"\n==============[ INFORMAÇÕES DO CARGO DE {cargo_atual['nome_cargo'].upper()} ]==============\n") 
        print(f"ID do Cargo.............: {cargo_atual['id_cargo']}")
        print(f"Nome do Cargo...........: {cargo_atual['nome_cargo']}")
        print(f"Área do Cargo...........: {cargo_atual['area_cargo']}")
        print(f"Descrição do Cargo......: {cargo_atual['descricao_cargo']}")
        print(f"Salário do Cargo........: R${cargo_atual['salario_cargo']}")

# atualiza um cargo
# quando atualizar o id, atualizar no funcionario tbm
def atualizar_cargo(id_cargo):
    try:
        cargo_atual = verificar_cargo(id_cargo) 
    except ValueError as e:
        print(e)
    else:
        while True:
            print("\n==============[ ATUALIZAÇÃO DOS DADOS DO CARGO ]==============\n")
            print("1 - Atualizar ID do Cargo")
            print("2 - Atualizar Nome do Cargo")
            print("3 - Atualizar Área do Cargo")
            print("4 - Atualizar Descrição do Cargo")
            print("5 - Atualizar Salário do Cargo")
            print("0 - Sair")
            op_atualizar = input("\nSelecione uma opção: ")
            if not op_atualizar.isdigit() or int(op_atualizar) > 5 or int(op_atualizar) < 0:
                print("\nSelecione uma opção válida.")
                continue
            op_atualizar = int(op_atualizar)
            if op_atualizar == 0:
                print('Retornando ao menu de cargo....')
                break
            match op_atualizar:
                case 1:
                    while True:
                        try:
                            id_cargo = input("Digite um novo ID para o Cargo (formato: CGXXXX. Ex: CG3213): ")
                            if re.match(regexIdCargo, id_cargo) is None:
                                raise ValueError("Digite um ID com formato válido.")
                            id_repetido = any(cargo['id_cargo'] == id_cargo for cargo in cargos)
                            if id_repetido:
                                raise ValueError("O ID digitado já existe.")
                        except ValueError as e:
                            print(e)
                        else:
                            for funcionario in funcionarios:
                                if funcionario['id_cargo'] == cargo_atual['id_cargo']:
                                    funcionario['id_cargo'] = id_cargo
                            cargo_atual['id_cargo'] = id_cargo
                            print('ID atualizado com sucesso.')
                            break
                case 2:
                    while True:
                        try:
                            nome_cargo = input("Digite o novo nome do cargo.................................: ")
                            if re.match(regexNome, nome_cargo) is None:
                                raise ValueError("Digite um nome válido.")
                        except ValueError as e:
                            print(e)
                        else:
                            cargo_atual['nome_cargo'] = nome_cargo
                            print('Nome do cargo atualizado com sucesso.')
                            break 
                case 3:
                    while True:
                        try:
                            area_cargo = input("Digite a nova área do cargo.............................: ")
                            if re.match(regexNome, nome_cargo) is None:
                                raise ValueError("Área do cargo inválida.")
                        except ValueError as e:
                            print(e)
                        else:
                            cargo_atual['area_cargo'] = area_cargo
                            print('Área atualizada com sucesso.')
                            break
                case 4:
                    while True:
                        try:
                            descricao_cargo = input("Digite a nova descrição do cargo........: ")
                            if descricao_cargo == '':
                                raise ValueError("Descrição vazia.")
                        except ValueError as e:
                            print(e)
                        else:
                            cargo_atual['descricao_cargo'] = descricao_cargo
                            print("Descrição atualizada com sucesso.")
                            break
                case 5:
                    try:
                        salario_cargo = float(input("Qual o novo salário do cargo?: "))
                        if salario_cargo < 0:
                            raise ValueError("Digite um valor maior que zero.")
                    except ValueError:
                        print("Digite um valor numérico válido para o salário.")
                    else:
                        cargo_atual['salario_cargo'] = salario_cargo
                        print("Salário do cargo atualizado com sucesso.")
                        break
    finally:
        print('\nRetornando ao menu de cargo.....')
# deleta um cargo
# OBS: Remover cargo do funcionario qnd deletar
def deletar_cargo(id_cargo):
    try: 
        cargo_atual = verificar_cargo(id_cargo)
    except ValueError as e:
        print(e)
    else:
        while True:
            op_delete = input(f"\nDeseja realmente remover o cargo {cargo_atual['nome_cargo']} de id {id_cargo}? S ou N: ")
            if op_delete.upper() != "S" and op_delete.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_delete.upper() == "S":
                cargos.remove(cargo_atual)
                print("\nCargo removido com sucesso.")
                for funcionario in funcionarios:
                    if funcionario['id_cargo'] == id_cargo:
                        funcionario['id_cargo'] = None
                break
            elif op_delete.upper() == "N":
                print("\nO cargo não foi removido.")
                break
    finally:
        print('\nRetornando ao menu de cargo.....')

# verifica a existencia do cargo, retornando um cargo
def verificar_cargo(id_cargo):
    if re.match(regexIdCargo, id_cargo) is None:
        raise ValueError("Digite um ID com formato válido.")
    cargo_atual = next((cargo for cargo in cargos if cargo['id_cargo'] == id_cargo), None)
    if cargo_atual is None:
        raise ValueError("\nO ID informado não pertence a um cargo cadastrado.")
    else:
        return cargo_atual

# visualiza todos os cargos
def visualizar_cargos():
    while True:
        try:
            if cargos == []:
                raise ValueError('Nenhum cargo cadastrado.')
        except ValueError as e:
            print(e)
        else:
            print("\n==============[ LISTA DE CARGOS ]==============")
            for cargo in cargos:
                read_cargo(cargo['id_cargo'])
            input('\nPressione ENTER para retornar: ')
            break
        finally:
            print('\nRetornando ao menu de cargos.....')

# FUNÇÕES DE FUNCIONÁRIO
# gerenciador de funcionario
def gerenciar_funcionario():
    print("\nIniciando menu de gerenciamento de Funcionário...") 
    while True:
        print("\n==============[ GERENCIAMENTO FUNCIONÁRIO ]==============\n")
        print("1 - Cadastrar Funcionário")
        print("2 - Visualizar informações do Funcionário")
        print("3 - Atualizar Funcionário")
        print("4 - Deletar Funcionário")
        print("0 - Sair")
        verif_gerenc_op = input("\nSelecione uma opção: ")
        if not verif_gerenc_op.isdigit() or int(verif_gerenc_op) > 4 or int(verif_gerenc_op) < 0:
            print("\nSelecione uma opção válida.")
            continue
        verif_gerenc_op = int(verif_gerenc_op)
        if verif_gerenc_op == 0:
            break
        elif verif_gerenc_op == 1:
            cadastrar_funcionario()
        elif verif_gerenc_op == 2:
            matricula_funcionario = input("Qual a matrícula do funcionário que deseja visualizar?: ")
            read_funcionario(matricula_funcionario)
        elif verif_gerenc_op == 3:
            matricula_funcionario = input("Qual a matrícula do funcionário que deseja atualizar?: ")
            atualizar_funcionario(matricula_funcionario)
        elif verif_gerenc_op == 4:
            matricula_funcionario = input("Qual a matrícula do funcionário que deseja deletar?: ")
            deletar_funcionario(matricula_funcionario)

# cadastrar um funcionario
def cadastrar_funcionario():
    print("Iniciando cadastro do funcionário...\n")
    funcionario = {}
    # cadastro matricula
    while True:
        try:
            matricula = input("Digite a matrícula do funcionário (formato: MXXXXX. Ex: M12345): ")
            if re.match(regexMatriculaFunc, matricula) is None:
                raise ValueError("Digite uma matrícula em formato válido.")
            matricula_repetida = any(funcionario['matricula_funcionario'] == matricula for funcionario in funcionarios)
            if matricula_repetida:
                raise ValueError("A matrícula inserida já foi cadastrada.")
        except ValueError as e:
            print(e) 
        else: 
            funcionario['matricula'] = matricula
            print('Matrícula registrada com sucesso.')
            break 
    # cadastro nome funcionario
    while True:
        try:
            nome = input("Digite o nome do funcionário...................................: ")
            if re.match(regexNome, nome) is None:
                raise ValueError("Digite um nome válido.")
        except ValueError as e:
            print(e) 
        else:
            funcionario['nome_funcionario'] = nome
            print('Nome registrado com sucesso.')
            break
    # cadastro horario de trabalho
    while True:
        try:
            horario_trabalho = input("Digite o horário de trabalho (ex: 08:00 - 17:30)...............: ")
            if re.match(regexHorarioFuncionamento, horario_trabalho) is None:
                raise ValueError("Digite um horário válido.")
        except ValueError as e:
            print(e) 
        else:
            funcionario['horario_trabalho'] = horario_trabalho
            print('Horário de trabalho registrado com sucesso.')
            break
    # cadastro centro automotivo
    while True:
        try:
            print("\n==============[ CENTROS AUTOMOTIVOS ]==============\n")
            for i in range(len(list(centros))):
                print(f"{i} - {list(centros)[i]['nome_centro']}")
            op_centro = input("\nEm qual centro automotivo o funcionário trabalha?: ")
            if not op_centro.isdigit() or int(op_centro) > (len(list(centros)) - 1) or int(op_centro) < 0:
                raise ValueError("\nSelecione uma opção válida.")
        except ValueError as e:
            print(e)
        else: 
            op_centro = int(op_centro)
            funcionario['id_centro'] = centros[op_centro]['id_centro']
            print('Centro automotivo registrado com sucesso.')
            break
    # cadastro cargo
    while True:
        try:
            print("\n==============[ CARGOS ]==============\n")
            for i in range(len(list(cargos))):
                print(f"{i} - {list(cargos)[i]['nome_cargo']}")
            op_cargo = input("\nQual o cargo do funcionário?: ")
            if not op_cargo.isdigit() or int(op_cargo) > (len(list(cargos)) - 1) or int(op_cargo) < 0:
                raise ValueError("\nSelecione uma opção válida.")
        except ValueError as e:
            print(e)
        else: 
            op_cargo = int(op_cargo)
            funcionario['id_cargo'] = cargos[op_cargo]['id_cargo']
            print('Cargo registrado com sucesso.')
            break
    funcionario['disponibilidade'] = True
    funcionarios.append(funcionario)
    print("\nFuncionário cadastrado com sucesso!")

# visualizar um funcionario
def read_funcionario(matricula):
    try:
        funcionario_atual = verificar_funcionario(matricula)
    except ValueError as e:
        print(e)
    else:
        print(f"\n==============[ INFORMAÇÕES DO FUNCIONÁRIO DE MATRÍCULA {funcionario_atual['matricula']} ]==============\n") 
        print(f"Matrícula.........................: {funcionario_atual['matricula']}")
        print(f"Nome do Funcionário...............: {funcionario_atual['nome_funcionario']}")
        print(f"Horário de Trabalho...............: {funcionario_atual['horario_trabalho']}")
        print(f"ID do Centro Automotivo...........: {funcionario_atual['id_centro']}")
        print(f"Centro Automotivo.................: {next((centro['nome_centro'] for centro in centros if centro['id_centro'] == funcionario_atual['id_centro']), None)}")
        print(f"ID do Cargo.......................: {funcionario_atual['id_cargo']}")
        print(f"Cargo.............................: {next((cargo['nome_cargo'] for cargo in cargos if cargo['id_cargo'] == funcionario_atual['id_cargo']), None)}")
        print(f"Salário...........................: R${next((cargo['salario_cargo'] for cargo in cargos if cargo['id_cargo'] == funcionario_atual['id_cargo']), None)}")
        print(f"Disponibilidade...................: {'Disponível' if funcionario_atual['disponibilidade'] == True else 'Indisponível'}")
        print("Pressione ENTER para voltar ao menu: ")
    finally:
        print('\nRetornando ao menu de funcionário.....')

# atualizar um funcionario
def atualizar_funcionario(matricula):
    try:
        funcionario_atual = verificar_funcionario(matricula)
    except ValueError as e:
        print(e)
    else:
        while True:
            print("\n==============[ ATUALIZAÇÃO DOS DADOS DO FUNCIONÁRIO ]==============\n")
            print("1 - Atualizar Matrícula")
            print("2 - Atualizar Nome do Funcionário")
            print("3 - Atualizar Horário de Trabalho")
            print("4 - Atualizar Centro Automotivo")
            print("5 - Atualizar Cargo")
            print("6 - Atualizar Disponibilidade")
            print("0 - Sair")
            op_atualizar = input("\nSelecione uma opção: ")
            if not op_atualizar.isdigit() or int(op_atualizar) > 6 or int(op_atualizar) < 0:
                print("\nSelecione uma opção válida.")
                continue
            op_atualizar = int(op_atualizar)
            if op_atualizar == 0:
                print('Retornando ao menu de cargo....')
                break
            match op_atualizar:
                case 1:
                    while True:
                        try:
                            matricula = input("Digite a nova matrícula do funcionário (formato: MXXXXX. Ex: M12345)................: ")
                            if re.match(regexMatriculaFunc, matricula) is None:
                                raise ValueError("Digite uma matrícula em formato válido.")
                            matricula_repetida = any(funcionario['matricula'] == matricula for funcionario in funcionarios)
                            if matricula_repetida:
                                raise ValueError("A matrícula inserida já foi cadastrada.")
                        except ValueError as e:
                            print(e)  
                        else:
                            funcionario_atual['matricula'] = matricula
                            print('Matrícula atualizada com sucesso.')
                            break
                case 2:
                    while True:
                        try:
                            nome = input("Digite o novo nome do funcionário............................: ")
                            if re.match(regexNome, nome) is None:
                                raise ValueError("Digite um nome válido.")
                        except ValueError as e:
                            print(e)
                        else:
                            funcionario_atual['nome_funcionario'] = nome
                            print('Nome atualizado com sucesso.')
                            break 
                case 3:
                   while True:
                        try:
                            horario_trabalho = input("Digite o novo horário de trabalho (ex: 08:00 - 17:30)..............: ")
                            if re.match(regexHorarioFuncionamento, horario_trabalho) is None:
                                raise ValueError("Digite um horário válido.")
                        except ValueError as e:
                            print(e) 
                        else:
                            funcionario_atual['horario_trabalho'] = horario_trabalho
                            print('Horário de trabalho atualizado com sucesso.')
                            break
                case 4:
                    while True:
                        try:
                            print("\n==============[ CENTROS AUTOMOTIVOS ]==============\n")
                            for i in range(len(list(centros))):
                                print(f"{i} - {list(centros)[i]['nome_centro']}")
                            op_centro = input("\nQual o novo Centro Automotivo que o funcionário irá trabalhar?: ")
                            if not op_centro.isdigit() or int(op_centro) > (len(list(centros)) - 1) or int(op_centro) < 0:
                                raise ValueError("\nSelecione uma opção válida.")
                        except ValueError as e:
                            print(e)
                        else: 
                            op_centro = int(op_centro)
                            funcionario_atual['id_centro'] = centros[op_centro]['id_centro']
                            print('Centro automotivo atualizado com sucesso.')
                            break
                case 5:
                     while True:
                        try:
                            print("\n==============[ CARGOS ]==============\n")
                            for i in range(len(list(cargos))):
                                print(f"{i} - {list(cargos)[i]['nome_cargo']}")
                            op_cargo = input("\nQual o novo cargo do funcionário?: ")
                            if not op_cargo.isdigit() or int(op_cargo) > (len(list(cargos)) - 1) or int(op_cargo) < 0:
                                raise ValueError("\nSelecione uma opção válida.")
                        except ValueError as e:
                            print(e)
                        else: 
                            op_cargo = int(op_cargo)
                            funcionario_atual['id_cargo'] = cargos[op_cargo]['id_cargo']
                            print('Cargo atualizado com sucesso.')
                            break
                case 6:
                    while True:
                        try:
                            disponibilidade_funcionario = input("Qual a disponibilidade do funcionário ('D' para Disponível e 'I' para Indisponível)?: ")
                            if disponibilidade_funcionario != 'D' and disponibilidade_funcionario != 'I':
                                raise ValueError("Digite uma opção válida.")
                            if disponibilidade_funcionario == 'D':
                                funcionario_atual['disponibilidade'] = True
                            elif disponibilidade_funcionario == 'I':
                                funcionario_atual['disponibilidade'] = False
                            print('Disponibilidade atualizada com sucesso.')
                            break
                        except ValueError as e:
                            print(e) 
    finally:
        print('\nRetornando ao menu de funcionário.....')

# deletar um funcionario
def deletar_funcionario(matricula):
    try: 
        funcionario_atual = verificar_funcionario(matricula)
    except ValueError as e:
        print(e)
    else:
        while True:
            op_delete = input(f"\nDeseja realmente remover o funcionário {funcionario_atual['nome_funcionario']} de ID {matricula}? S ou N: ")
            if op_delete.upper() != "S" and op_delete.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_delete.upper() == "S":
                funcionarios.remove(funcionario_atual)
                print("\nfuncionário removido com sucesso.")
                break
            elif op_delete.upper() == "N":
                print("\nO funcionário não foi removido.")
                break
    finally:
        print('\nRetornando ao menu de funcionário.....')

# verifica a existencia de um funcionario, retornando um
def verificar_funcionario(matricula):
    if re.match(regexMatriculaFunc, matricula) is None:
        raise ValueError("Digite uma matrícula em formato válido.")
    funcionario_atual = next((funcionario for funcionario in funcionarios if funcionario['matricula'] == matricula), None)
    if funcionario_atual is None:
        raise ValueError("\nA matrícula informada não pertence a um funcionário cadastrado.")
    else:
        return funcionario_atual

# FUNÇÕES DE SERVIÇO
# gerenciar servico
# id_servico, tipo_servico, descricao_servico, preco_servico, duracao
# deve estar ligado ao diagnostico e agendamento
# quando deletado, deve ser removido do diagnostico e do agendamento
# quando atualizado o id, deve ser atualizado no diagnostico e no agendamento
def gerenciar_servico():
    print("\nIniciando menu de gerenciamento de Serviço...") 
    while True:
        print("\n==============[ GERENCIAMENTO SERVIÇO ]==============\n")
        print("1 - Cadastrar Serviço")
        print("2 - Visualizar informações do Serviço")
        print("3 - Atualizar Serviço")
        print("4 - Deletar Serviço")
        print("5 - Visualizar todos os Serviços")
        print("0 - Sair")
        verif_gerenc_op = input("\nSelecione uma opção: ")
        if not verif_gerenc_op.isdigit() or int(verif_gerenc_op) > 5 or int(verif_gerenc_op) < 0:
            print("\nSelecione uma opção válida.")
            continue
        verif_gerenc_op = int(verif_gerenc_op)
        if verif_gerenc_op == 0:
            break
        elif verif_gerenc_op == 1:
            criar_servico()
        elif verif_gerenc_op == 2:
            id_servico = input("Qual o ID do serviço que deseja visualizar?: ")
            read_servico(id_servico)
        elif verif_gerenc_op == 3:
            id_servico = input("Qual o ID do serviço que deseja atualizar?: ")
            atualizar_servico(id_servico)
        elif verif_gerenc_op == 4:
            id_servico = input("Qual o ID do serviço que deseja deletar?: ")
            deletar_servico(id_servico)
        elif verif_gerenc_op == 5:
            visualizar_servicos()

# cria um novo serviço
def criar_servico():
    print("Iniciando cadastro de serviço...\n")
    servico = {}
    # cadastro id do serviço
    while True:
        try:
            id_servico = input("Digite um ID para o Serviço (formato: SVXXXX. Ex: SV1234)....: ")
            if re.match(regexIdServico, id_servico) is None:
                raise ValueError("Digite um ID com formato válido.")
            id_repetido = any(servico['id_servico'] == id_servico for servico in servicos)
            if id_repetido:
                raise ValueError("O ID digitado já existe.")
        except ValueError as e:
            print(e)
        else:
            servico['id_servico'] = id_servico
            print('ID registrado com sucesso.')
            break
    # cadastro tipo do serviço
    while True:
        try:
            tipo_servico = input("Digite o tipo do serviço.....................................: ")
            if re.match(regexNome, tipo_servico) is None:
                raise ValueError("Digite um tipo válido.")
        except ValueError as e:
            print(e)  
        else:
            servico['tipo_servico'] = tipo_servico
            print('Tipo do serviço registrado com sucesso.')
            break
    # cadastro descrição do serviço
    while True:
        try:
            descricao_servico = input("Digite a descrição do serviço................................: ")
            if descricao_servico == '':
                raise ValueError("Descrição vazia. Digite novamente.")
        except ValueError as e:
            print(e)
        else:
            servico['descricao_servico'] = descricao_servico
            print('Descrição registrada com sucesso.')
            break
    # cadastro preço do serviço
    while True:
        try:
            preco = float(input("Digite o preço do serviço....................................: "))
            if preco < 0:
                raise ValueError("Digite um valor maior que zero para o preço do serviço.")
        except ValueError:
            print("Digite um valor numérico válido.")
        else:
            servico['preco'] = preco
            print("Preço registrado com sucesso.")
            break
    # cadastro duracao do serviço
    while True:
        try:
            duracao = input("Qual a duração do serviço? (formato: X-Y horas Ex: 1-2 horas): ")
            if re.match(regexDuracao, duracao) is None:
                raise ValueError("Formato inválido para a duração.")
        except ValueError as e:
            print(e) 
        else:
            servico['duracao'] = duracao
            print("Duração registrada com sucesso.")
            break
    servicos.append(servico)
    print(f"\nServiço de ID: {id_servico} cadastrado com sucesso!") 

# visualiza os dados do serviço
def read_servico(id_servico):
    try:
        servico_atual = verificar_servico(id_servico)
    except ValueError as e:
        print(e)
    else:
        print(f"\n==============[ INFORMAÇÕES DO SERVIÇO DE ID {servico_atual['id_servico']} ]==============\n") 
        print(f"ID do Serviço....................: {servico_atual['id_servico']}")
        print(f"Tipo do Serviço..................: {servico_atual['tipo_servico']}")
        print(f"Descrição do Serviço.............: {servico_atual['descricao_servico']}")
        print(f"Preço do Serviço.................: R${servico_atual['preco']}")
        print(f"Duração do Serviço...............: {servico_atual['duracao']}")

# atualiza os dados do serviço
def atualizar_servico(id_servico):
    servico_atual = {}
    try:
        servico_atual = verificar_servico(id_servico)
    except ValueError as e:
        print(e)
    else:
        while True:
            print("\n==============[ ATUALIZAÇÃO DOS DADOS DO SERVIÇO ]==============\n")
            print("1 - Atualizar ID do Serviço")
            print("2 - Atualizar Tipo do Serviço")
            print("3 - Atualizar Descrição do Serviço")
            print("4 - Atualizar Preço do Serviço")
            print("5 - Atualizar Duração do Serviço")
            print("0 - Sair")
            op_atualizar = input("\nSelecione uma opção: ")
            if not op_atualizar.isdigit() or int(op_atualizar) > 5 or int(op_atualizar) < 0:
                print("\nSelecione uma opção válida.")
                continue
            op_atualizar = int(op_atualizar)
            if op_atualizar == 0:
                print('Retornando ao menu de serviço....')
                break
            match op_atualizar:
                case 1:
                    while True:
                        try:
                            id_servico = input("Digite um novo ID para o Serviço (formato: SVXXXX. Ex: SV0032): ")
                            if re.match(regexIdServico, id_servico) is None:
                                raise ValueError("Digite um ID com formato válido.")
                            id_repetido = any(servico['id_servico'] == id_servico for servico in servicos)
                            if id_repetido:
                                raise ValueError("O ID digitado já existe.")
                        except ValueError as e:
                            print(e)
                        else:
                            for agendamento in agendamentos: # altera o id do servico no agendamento
                                if agendamento['id_servico'] == servico_atual['id_servico']:
                                    agendamento['id_servico'] = id_servico
                            for diagnostico in diagnosticos: # altera o id do servico no diagnostico
                                if diagnostico['id_servico'] == servico_atual['id_servico']:
                                    diagnostico['id_servico'] = id_servico
                            servico_atual['id_servico'] = id_servico
                            print('ID atualizado com sucesso.')
                            break
                case 2:
                    while True:
                        try:
                            tipo_servico = input("Digite o novo tipo do serviço.................................: ")
                            if re.match(regexNome, tipo_servico) is None:
                                raise ValueError("Digite um tipo válido.")
                        except ValueError as e:
                            print(e)
                        else:
                            servico_atual['tipo_servico'] = tipo_servico
                            print('Tipo do serviço atualizado com sucesso.')
                            break  
                case 3:
                   while True:
                        try:
                            descricao_servico = input("Digite a nova descrição do serviço.............................: ")
                            if descricao_servico == '':
                                raise ValueError("Descrição vazia. Digite novamente.")
                        except ValueError as e:
                            print(e)
                        else:
                            servico_atual['descricao_servico'] = descricao_servico
                            print('Descrição atualizada com sucesso.')
                            break
                case 4:
                    while True:
                        try:
                            preco = float(input("Digite o preço do serviço........: "))
                            if preco < 0: 
                                raise ValueError("Digite um valor maior que zero para o preço do serviço.")
                        except ValueError:
                            print("Digite um valor numérico válido.")
                        else:
                            servico_atual['preco'] = preco
                            print("Preço atualizado com sucesso.")
                            break
                case 5:
                     while True:
                        try:
                            duracao = input("Qual a duração do serviço? (formato: X-Y horas Ex: 1-2 horas): ")
                            if re.match(regexDuracao, duracao) is None:
                                raise ValueError("Formato inválido para a duração.")
                        except ValueError as e:
                            print(e) 
                        else:
                            servico_atual['duracao'] = duracao
                            print("Duração atualizada com sucesso.")
                            break
    finally:
        print('\nRetornando ao menu de serviço.....')

# deleta um serviço
def deletar_servico(id_servico):
    try: 
        servico_atual = verificar_servico(id_servico)
    except ValueError as e:
        print(e)
    else:
        while True:
            op_delete = input(f"\nDeseja realmente deletar o serviço de {servico_atual['tipo_servico']} de ID {id_servico}? S ou N: ")
            if op_delete.upper() != "S" and op_delete.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_delete.upper() == "S":
                servicos.remove(servico_atual)
                for agendamento in agendamentos:
                    if agendamento['id_servico'] == id_servico: #remove o serviço do agendamento
                        deletar_agendamento(agendamento['agendamento_id'], True)
                for diagnostico in diagnosticos:
                    if diagnostico['id_servico'] == id_servico: # remove o diagnostico
                        deletar_diagnostico(diagnostico['id_diagnostico'], True)
                #falta do diagnostico
                print("\nServiço removido com sucesso.")
                break
            elif op_delete.upper() == "N":
                print("\nServiço não foi removido.")
                break
    finally:
        print('\nRetornando ao menu de serviço.....')

# verifica a existencia do servico, retornando
def verificar_servico(id_servico):
    if re.match(regexIdServico, id_servico) is None:
        raise ValueError("Digite um ID com formato válido.")
    servico_atual = next((servico for servico in servicos if servico['id_servico'] == id_servico), None)
    if servico_atual is None:
        raise ValueError("\nO ID informado não pertence a um serviço cadastrado.")
    else:
        return servico_atual

# visualiza todos os serviços
def visualizar_servicos():
    while True:
        try:
            if servicos == []:
                raise ValueError('Nenhum serviço cadastrado.')
        except ValueError as e:
            print(e)
        else:
            print("\n==============[ LISTA DE SERVIÇOS ]==============")
            for servico in servicos:
                read_servico(servico['id_servico'])
            input('\nPressione ENTER para retornar: ')
            break
        finally:
            print('\nRetornando ao menu de serviço.....')

#FUNÇÕES DE PEÇAS
# gerenciar peça
def gerenciar_pecas():
    print("\nIniciando menu de gerenciamento de Peça...") 
    while True:
        print("\n==============[ GERENCIAMENTO PEÇA ]==============\n")
        print("1 - Cadastrar Peça")
        print("2 - Visualizar informações da Peça")
        print("3 - Atualizar Peça")
        print("4 - Deletar Peça")
        print("5 - Visualizar todas as Peças")
        print("0 - Sair")
        verif_gerenc_op = input("\nSelecione uma opção: ")
        if not verif_gerenc_op.isdigit() or int(verif_gerenc_op) > 5 or int(verif_gerenc_op) < 0:
            print("\nSelecione uma opção válida.")
            continue
        verif_gerenc_op = int(verif_gerenc_op)
        if verif_gerenc_op == 0:
            break
        elif verif_gerenc_op == 1:
            cadastrar_peca()
        elif verif_gerenc_op == 2:
            id_peca = input("Qual o ID da peça que deseja visualizar?: ")
            read_peca(id_peca)
        elif verif_gerenc_op == 3:
            id_peca = input("Qual o ID da peça que deseja atualizar?: ")
            atualizar_peca(id_peca)
        elif verif_gerenc_op == 4:
            id_peca = input("Qual o ID da peça que deseja deletar?: ")
            deletar_peca(id_peca)
        elif verif_gerenc_op == 5:
            visualizar_pecas()

# cadastra uma peça
def cadastrar_peca():
    print("Iniciando cadastro da peça...\n")
    peca = {}
    # cadastro id peca
    while True:
        try:
            id_peca = input("Digite o ID da peça (formato: PXXXXX. Ex: P12345): ")
            if re.match(regexIdPeca, id_peca) is None:
                raise ValueError("Digite um ID em formato válido.")
            id_peca_repetida = any(peca['id_peca'] == id_peca for peca in pecas)
            if id_peca_repetida:
                raise ValueError("O ID inserido já foi cadastrado.")
        except ValueError as e:
            print(e)
        else:
            peca['id_peca'] = id_peca
            print('ID registrado com sucesso.')
            break

    # cadastro nome peca
    while True:
        try:
            nome_peca = input("Digite o nome da peça............................: ")
            if re.match(regexNome, nome_peca) is None:
                raise ValueError("Digite um nome válido.")
        except ValueError as e:
            print(e)
        else:
            peca['nome_peca'] = nome_peca
            print('Nome registrado com sucesso.')
            break
    # cadastro disponivel
    while True:
        try:
            qtnd_disponivel = int(input("Digite a quantidade disponível...................: "))
            if qtnd_disponivel < 0:
                raise ValueError("Digite uma quantidade maior que zero")      
        except ValueError:
            print("Digite um valor numérico válido.") 
        else:
            peca['quantidade_disponivel'] = qtnd_disponivel
            print('Quantidade disponível registrada com sucesso.')
            break
    # cadastro preço
    while True:
        try:
            preco = float(input("Qual o preço da peça?............................: "))
            if preco < 0:
                raise ValueError("Digite um valor maior que zero.")                
        except ValueError:
            print("Digite um valor válido para o preço da peça.") 
        else:
            peca['preco'] = preco
            print('Preço registrado com sucesso.')
            break
    pecas.append(peca)
    print("\nPeça cadastrada com sucesso!")

# visualiza uma peça
def read_peca(id_peca):
    try:
        peca_atual = verificar_peca(id_peca)
    except ValueError as e:
        print(e)
    else:
        print(f"\n==============[ INFORMAÇÕES DA PEÇA DE ID {peca_atual['id_peca']} ]==============\n") 
        print(f"ID da Peça....................: {peca_atual['id_peca']}")
        print(f"Nome da Peça..................: {peca_atual['nome_peca']}")
        print(f"Quantidade Disponível.........: {peca_atual['quantidade_disponivel'] if peca_atual['quantidade_disponivel'] > 0 else 'Indisponível'}")
        print(f"Preço da Peça.................: R${peca_atual['preco']}")

# atualiza uma peça
def atualizar_peca(id_peca):
    try:
        peca_atual = verificar_peca(id_peca)
    except ValueError as e:
        print(e)
    else:
        while True:
            print("\n==============[ ATUALIZAÇÃO DOS DADOS DA PEÇA ]==============\n")
            print("1 - Atualizar ID do Peça")
            print("2 - Atualizar Nome da Peça")
            print("3 - Atualizar Quantidade disponível da Peça")
            print("4 - Atualizar Preço da peça")
            print("0 - Sair")
            op_atualizar = input("\nSelecione uma opção: ")
            if not op_atualizar.isdigit() or int(op_atualizar) > 4 or int(op_atualizar) < 0:
                print("\nSelecione uma opção válida.")
                continue
            op_atualizar = int(op_atualizar)
            if op_atualizar == 0:
                print('Retornando ao menu de peça....')
                break
            match op_atualizar:
                case 1:
                    while True:
                        try:
                            id_peca = input("Digite o novo ID da peça (formato: PXXXXX. Ex: P12345)................: ")
                            if re.match(regexIdPeca, id_peca) is None:
                                raise ValueError("Digite um ID em formato válido.")
                            id_peca_repetida = any(peca['id_peca'] == id_peca for peca in pecas)
                            if id_peca_repetida:
                                raise ValueError("O ID inserido já foi cadastrado.")
                        except ValueError as e:
                            print(e) 
                        else:
                            peca_atual['id_peca'] = id_peca
                            print('\nID atualizado com sucesso.')
                            break
                case 2:
                    while True:
                        try:
                            nome_peca = input("Digite o novo nome da peça............................: ")
                            if re.match(regexNome, nome_peca) is None:
                                raise ValueError("Digite um nome válido.")
                        except ValueError as e:
                            print(e)  
                        else: 
                            peca_atual['nome_peca'] = nome_peca
                            print('\nNome atualizado com sucesso.')
                            break
                case 3:
                    while True:
                        try:
                            qtnd_disponivel = int(input("Digite a nova quantidade disponível..............: "))
                            if qtnd_disponivel < 0:
                                raise ValueError("Digite uma quantidade maior que zero")      
                        except ValueError:
                            print("Digite um valor numérico válido.")
                        else:
                            peca_atual['quantidade_disponivel'] = qtnd_disponivel
                            print('\nQuantidade disponível atualizada com sucesso.')
                            break
                case 4:
                    while True:
                        try:
                            preco = float(input("Qual o novo preço da peça?: "))
                            if preco < 0:
                                raise ValueError("Digite um valor maior que zero.")
                        except ValueError:
                            print("Digite um valor válido para o preço da peça.") 
                        else:
                            peca_atual['preco'] = preco
                            print('\nPreço atualizado com sucesso.')
                            break
    finally:
        print('\nRetornando ao menu de peça.....')

# deleta uma peça
def deletar_peca(id_peca):
    try: 
        peca_atual = verificar_peca(id_peca)
    except ValueError as e:
        print(e)
    else:
        while True:
            op_delete = input(f"\nDeseja realmente remover a Peça {peca_atual['nome_peca']} de ID {id_peca}? S ou N: ")
            if op_delete.upper() != "S" and op_delete.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_delete.upper() == "S":
                # remover o autoorcamento - TO DO
                pecas.remove(peca_atual)
                print("\nPeça removida com sucesso.")
                break
            elif op_delete.upper() == "N":
                print("\nA Peça não foi removida.")
                break 
    finally:
        print('\nRetornando ao menu de peça.....')       

# verifica a existencia da peca, retornando a peca
def verificar_peca(id_peca):
    peca_atual = next((peca for peca in pecas if peca['id_peca'] == id_peca), None)
    if peca_atual is None:
        raise ValueError("\nO ID informado não pertence a uma peça cadastrada ou está incorreto.")
    else:
        return peca_atual

# visualizar todas as peças
def visualizar_pecas():
    while True:
        try:
            if pecas == []:
                raise ValueError('Nenhuma peça cadastrada.')
        except ValueError as e:
            print(e)
        else:
            print("\n==============[ LISTA DE PEÇAS ]==============")
            for peca in pecas:
                read_peca(peca['id_peca'])
            input('\nPressione ENTER para retornar: ')    
            break
        finally:
            print('\nRetornando ao menu de peça.....')

#FUNÇÕES DO AUTODIAGNÓSTICO
# gerenciar autodiagnostico
def gerenciar_autodiagnostico():
    print("\nIniciando menu de gerenciamento de Autodiagnóstico...") 
    while True:
        print("\n==============[ GERENCIAMENTO AUTODIAGNÓSTICO ]==============\n")
        print("1 - Realizar Autodiagnóstico")
        print("2 - Visualizar informações do Autodiagnóstico")
        print("3 - Atualizar ID do Autodiagnóstico")
        print("4 - Deletar Autodiagnóstico")
        print("0 - Sair")
        verif_gerenc_op = input("\nSelecione uma opção: ")
        if not verif_gerenc_op.isdigit() or int(verif_gerenc_op) > 4 or int(verif_gerenc_op) < 0:
            print("\nSelecione uma opção válida.")
            continue
        verif_gerenc_op = int(verif_gerenc_op)
        if verif_gerenc_op == 0:
            break
        elif verif_gerenc_op == 1:
            placa_input = input("Qual a placa do veículo que irá ser diagnosticado?.....................: ")
            problema_relatado = input("Qual o problema identificado no veículo? (quanto mais detalhes, melhor): ")
            auto_diagnostico(placa_input, problema_relatado)
        elif verif_gerenc_op == 2:
            id_diagnostico = input("Qual o ID do diagnóstico que deseja visualizar?: ")
            read_autodiagnostico(id_diagnostico)
        elif verif_gerenc_op == 3:
            id_diagnostico = input("Qual o ID do diagnóstico que deseja atualizar?: ")
            atualizar_diagnostico_id(id_diagnostico)
        elif verif_gerenc_op == 4:
            id_diagnostico = input("Qual o ID do diagnóstico que deseja deletar?: ")
            deletar_diagnostico(id_diagnostico, False)

# novo autodiagnostico, com pontuação para cada palavra encontrada
def auto_diagnostico(placa, problema_relatado):
    diagnostico = {}
    diagnosticos_possiveis = {}
    try:
        veiculo_atual = verificar_veiculo(placa)
        for problema, detalhes in problemas_carro.items():
            soma_pesos = 0
            for sintoma, peso in detalhes['sintomas'].items():
                if sintoma in problema_relatado.lower():
                    soma_pesos += peso
            if soma_pesos > 0:
                diagnosticos_possiveis[problema] = soma_pesos
        if diagnosticos_possiveis == {}:
            raise ValueError("\nNenhuma solução encontrada para o problema descrito. Tente novamente, explicando com mais detalhes.")
    except ValueError as e:
        print(e)
    else:
        diagnosticos_possiveis = sorted(diagnosticos_possiveis.items(), key=lambda x: x[1], reverse=True)
        diagnostico['id_diagnostico'] = str(uuid.uuid4())
        diagnostico['descricao_sintomas'] = problema_relatado
        diagnostico['problema'] = diagnosticos_possiveis[0][0]
        diagnostico['solucao'] = problemas_carro[diagnosticos_possiveis[0][0]]['solução']
        diagnostico['placa_veiculo'] = veiculo_atual['placa']
        diagnostico['cpf_usuario'] = veiculo_atual['cpf_proprietario']
        diagnostico['id_servico'] = next((servico['id_servico'] for servico in servicos if servico['descricao_servico'] == diagnostico['solucao']), None)
        diagnosticos.append(diagnostico)
        print("\nEste foi o problema principal identificado pelo autodiagnóstico: \n")
        print(f"{diagnosticos_possiveis[0][0]} - Probabilidade: {diagnosticos_possiveis[0][1]}")
        print(f"\nAutodiagnóstico realizado com sucesso! O ID do diagnóstico é: {diagnostico['id_diagnostico']}")
    finally:
        print('\nRetornando ao menu de autodiagnóstico.....')

# visualizar informacoes do autodiagnostico feito
def read_autodiagnostico(id_diagnostico):
    try:
        diagnostico_atual = verificar_diagnostico(id_diagnostico)
    except ValueError as e:
        print(e)
    else:
        print("\n==============[ INFORMAÇÕES DO AUTODIAGNÓSTICO ]==============\n") 
        print(f"ID do diagnóstico.........: {diagnostico_atual["id_diagnostico"]}")
        print(f"Descrição dos sintomas....: {diagnostico_atual["descricao_sintomas"]}")
        print(f"Problema identificado.....: {diagnostico_atual["problema"]}")
        print(f"Solução...................: {diagnostico_atual["solucao"]}")
        print(f"ID do Serviço.............: {diagnostico_atual["id_servico"]}")
        print(f"Tipo do Serviço...........: {next((servico['tipo_servico'] for servico in servicos if servico['id_servico'] == diagnostico_atual['id_servico']), None)}")
        print(f"Placa do veículo analisado: {diagnostico_atual["placa_veiculo"]}")
        print(f"CPF do Proprietário.......: {next((veiculo['cpf_proprietario'] for veiculo in veiculos if veiculo['placa'] == diagnostico_atual['placa_veiculo']), None)}")
        input("\nPressione ENTER para voltar ao menu: ") 
    finally:
        print("\nRetornando ao menu de autodiagnóstico...")

# atualiza o ID do autodiagnostico
def atualizar_diagnostico_id(id_diagnostico):
    try:
        diagnostico_atual = verificar_diagnostico(id_diagnostico)
    except ValueError as e:
        print(e)
    else:
         while True:
            op_atualizar_id = input(f"\nDeseja realmente atualizar o ID do diagnóstico? S ou N: ")
            if op_atualizar_id.upper() != "S" and op_atualizar_id.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_atualizar_id.upper() == "S":
                diagnostico_atual['id_diagnostico'] = str(uuid.uuid4())
                print(f"\nID do diagnóstico atualizado com sucesso! O novo ID é: {diagnostico_atual['id_diagnostico']}")
                break
            elif op_atualizar_id.upper() == "N":
                print("\nO ID do diagnóstico não foi atualizado.")
                break
    finally:
        print("\nRetornando ao menu de autodiagnóstico...")

# deleta o diagnostico
def deletar_diagnostico(id_diagnostico, type):
    try: 
        diagnostico_atual = verificar_diagnostico(id_diagnostico)
    except ValueError as e:
        print(e)
        print("\nRetornando ao menu de diagnóstico...")
    else:
        while True:
            if type == True: # caso o diagnostico tenha q ser removido devido a remoção de outro
                diagnosticos.remove(diagnostico_atual)
                for orcamento in orcamentos:
                    if orcamento['id_diagnostico'] == id_diagnostico:
                        deletar_orcamento(orcamento['id_orcamento'], True)
                break
            op_delete = input(f"\nDeseja realmente remover o Diagnóstico de ID {id_diagnostico}? S ou N: ")
            if op_delete.upper() != "S" and op_delete.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_delete.upper() == "S":
                diagnosticos.remove(diagnostico_atual)
                for orcamento in orcamentos:
                    if orcamento['id_diagnostico'] == id_diagnostico:
                        deletar_diagnostico(orcamento['id_diagnostico'], True)
                print("\nDiagnóstico removido com sucesso.")
                print("\nRetornando ao menu de diagnóstico...")
                break
            elif op_delete.upper() == "N":
                print("\nO Diagnóstico não foi removido.")
                break  

# verifica existencia do diagnostico, retornando um caso sim
def verificar_diagnostico(id_diagnostico):
    diagnostico_atual = next((diagnostico for diagnostico in diagnosticos if diagnostico['id_diagnostico'] == id_diagnostico), None)
    if diagnostico_atual is None:
        raise ValueError("\nO ID informado não pertence a um diagnóstico feito ou está incorreto.")
    else:
        return diagnostico_atual

# FUNÇÕES DO AUTOORÇAMENTO
# gerencia auto-orcamento
def gerenciar_orcamento():
    print("\nIniciando menu de gerenciamento de Auto-orçamento...") 
    while True:
        print("\n==============[ GERENCIAMENTO AUTO-ORÇAMENTO ]==============\n")
        print("1 - Realizar Auto-orçamento")
        print("2 - Visualizar informações do Auto-orçamento")
        print("3 - Atualizar ID do Auto-orçamento")
        print("4 - Deletar Auto-orçamento")
        print("0 - Sair")
        verif_gerenc_op = input("\nSelecione uma opção: ")
        if not verif_gerenc_op.isdigit() or int(verif_gerenc_op) > 4 or int(verif_gerenc_op) < 0:
            print("\nSelecione uma opção válida.")
            continue
        verif_gerenc_op = int(verif_gerenc_op)
        if verif_gerenc_op == 0:
            break
        elif verif_gerenc_op == 1:
            id_diagnostico = input("Qual o ID do diagnóstico que deseja realizar um auto-orçamento?: ")
            auto_orcamento(id_diagnostico)
        elif verif_gerenc_op == 2:
            id_orcamento = input("Qual o ID do auto-orçamento que deseja visualizar?: ")
            read_orcamento(id_orcamento)
        elif verif_gerenc_op == 3:
            id_orcamento = input("Qual o ID do auto-orçamento que deseja atualizar?: ")
            atualizar_id_orcamento(id_orcamento)
        elif verif_gerenc_op == 4:
            id_orcamento = input("Qual o ID do auto-orçamento que deseja deletar?: ")
            deletar_orcamento(id_orcamento, False)

# auto-orçamento com base no pré-diagnóstico
def auto_orcamento(id_diagnostico):
    orcamento = {}
    try:
        diagnostico_atual = verificar_diagnostico(id_diagnostico)
    except ValueError as e:
        print(e)
    else:
        orcamento['id_diagnostico'] = diagnostico_atual['id_diagnostico']
        orcamento['id_orcamento'] = str(uuid.uuid4())
        orcamento['valor'] = next((servico['preco'] for servico in servicos if servico['id_servico'] == diagnostico_atual['id_servico']), None)
        orcamentos.append(orcamento)
        print(f'\nAuto-orçamento criado com sucesso! O ID do orçamento é {orcamento['id_orcamento']}')
    finally:
        print("\nRetornando ao menu de orçamento...")

# visualiza auto-orcamento                   
def read_orcamento(id_orcamento):
    try:
        orcamento_atual = verificar_orcamento(id_orcamento)
    except ValueError as e:
        print(e)
    else:
        print("\n==============[ ORÇAMENTO ]==============\n")
        print(f"ID do Orçamento....: {orcamento_atual['id_orcamento']}")
        print(f"Serviço............: {next((servico['tipo_servico'] for servico in servicos if servico['id_servico'] == (next((diagnostico['id_servico'] for diagnostico in diagnosticos if diagnostico['id_diagnostico'] == orcamento_atual['id_diagnostico']), None))), None)}")
        print(f"Valor..............: R${orcamento_atual['valor']}")
        input("\nPressione ENTER para voltar ao menu: ")
    finally:
        print("\nRetornando ao menu de orçamento...")
        
# atualiza o id do orcamento
def atualizar_id_orcamento(id_orcamento):
    try:
        orcamento_atual = verificar_orcamento(id_orcamento)
    except ValueError as e:
        print(e)
    else:
         while True:
            op_atualizar_id = input(f"\nDeseja realmente atualizar o ID do orçamento? S ou N: ")
            if op_atualizar_id.upper() != "S" and op_atualizar_id.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_atualizar_id.upper() == "S":
                orcamento_atual['id_orcamento'] = str(uuid.uuid4())
                print(f"\nID do orçamento atualizado com sucesso! O novo ID é: {orcamento_atual['id_orcamento']}")
                break
            elif op_atualizar_id.upper() == "N":
                print("\nO ID do orçamento não foi atualizado.")
                break
    finally:
        print("\nRetornando ao menu de orçamento...")

# deleta o orcamento
def deletar_orcamento(id_orcamento, type):
    try: 
        orcamento_atual = verificar_orcamento(id_orcamento)
    except ValueError as e:
        print(e)
        print("\nRetornando ao menu de orçamento...")
    else:
        while True:
            if type == True: # caso o diagnostico tenha q ser removido devido a remoção de outro
                orcamentos.remove(orcamento_atual)
                break
            op_delete = input(f"\nDeseja realmente remover o Orçamento de ID {id_orcamento}? S ou N: ")
            if op_delete.upper() != "S" and op_delete.upper() != "N":
                print("\nDigite uma opção válida.")
                continue
            elif op_delete.upper() == "S":
                diagnosticos.remove(orcamento_atual)
                print("\nOrçamento removido com sucesso.")
                print("\nRetornando ao menu de orçamento...")
                break
            elif op_delete.upper() == "N":
                print("\nO Orçamento não foi removido.")
                break 
        
# verifica existencia do autoorçamento
def verificar_orcamento(id_orcamento):
    orcamento_atual = next((orcamento for orcamento in orcamentos if orcamento['id_orcamento'] == id_orcamento), None)
    if orcamento_atual is None:
        raise ValueError("\nO ID informado não pertence a um orçamento feito ou está incorreto.")
    else:
        return orcamento_atual

# menu inicial
while True:
    print("\n==============[ MENU DO SISTEMA ]==============\n")
    print("1  - Gerenciar Usuário")
    print("2  - Gerenciar Veículo")
    print("3  - Gerenciar Agendamentos")
    print("4  - Gerenciar Peças")
    print("5  - Gerenciar Centro Automotivo")
    print("6  - Gerenciar Cargo")
    print("7  - Gerenciar Funcionário")
    print("8  - Gerenciar Serviço")
    print("9  - Gerenciar Autodiagnóstico")
    print("10 - Gerenciar Auto-orçamento")
    print("0  - Sair\n")
    option = input("Opção: ")
    if not option.isdigit() or (int(option) > 10 or int(option) < 0):
        print("\nSelecione uma opção válida.")
        continue
    option = int(option)
    if option == 0:
        print("\nSolicitação encerrada.\n")
        break
    elif option == 1:
        gerenciar_usuario()
    elif option == 2:
        gerenciar_veiculo()      
    elif option == 3:
        gerenciar_agendamento()    
    elif option == 4:
        gerenciar_pecas()   
    elif option == 5:
        gerenciar_centro()
    elif option == 6:
        gerenciar_cargo()
    elif option == 7:
        gerenciar_funcionario()
    elif option == 8:
        gerenciar_servico()
    elif option == 9:
        gerenciar_autodiagnostico()
    elif option == 10:
        gerenciar_orcamento()
        
        