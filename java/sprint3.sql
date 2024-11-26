CREATE TABLE usuario
(
    cpf_usuario CHAR(11) CONSTRAINT usuario_cpf_pk PRIMARY KEY,
    nome_usuario VARCHAR(80) CONSTRAINT usuario_nm_nn NOT NULL
                          CONSTRAINT usuario_nm_unique UNIQUE,
    email VARCHAR(255) CONSTRAINT usuario_mail_nn NOT NULL,
    telefone CHAR(11) CONSTRAINT usuario_tel_nn NOT NULL,
    senha VARCHAR(30) CONSTRAINT usuario_sen_nn NOT NULL,
    CONSTRAINT chk_senha_usuario CHECK (LENGTH(senha) > 6)
);

CREATE TABLE veiculo
(
    placa CHAR(7) CONSTRAINT veic_pl_pk PRIMARY KEY,
    marca VARCHAR(50) CONSTRAINT veic_mrc_nn NOT NULL,
    modelo VARCHAR(50) CONSTRAINT veic_mdl_nn NOT NULL,
    ano NUMBER(4) CONSTRAINT veic_ano_nn NOT NULL, 
    quilometragem NUMBER(10,2) CONSTRAINT veic_quil_nn NOT NULL, 
    CONSTRAINT chk_quil_veiculo CHECK (quilometragem > 0),
    usuario_cpf_usuario CHAR(11) CONSTRAINT veic_cpf_fk REFERENCES usuario(cpf_usuario) ON DELETE CASCADE    
);
 
CREATE TABLE orcamento 
(
    id_orcamento CHAR(36) CONSTRAINT orc_id_pk PRIMARY KEY,
    descricao_orcamento VARCHAR(255) CONSTRAINT orc_desc_nn NOT NULL,
    valor_total NUMBER(9,2) CONSTRAINT orc_vt_nn NOT NULL,
    status_orcamento VARCHAR(50) CONSTRAINT orc_stts_nn NOT NULL,
    CONSTRAINT chk_status_orcamento CHECK (status_orcamento IN ('PENDENTE', 'APROVADO', 'REJEITADO'))
);
CREATE TABLE servico
(
    id_servico CHAR(6) CONSTRAINT serv_id_pk PRIMARY KEY,
    tipo_servico VARCHAR(50) CONSTRAINT serv_tp_nn NOT NULL,
    descricao_servico VARCHAR(255) CONSTRAINT serv_desc_nn NOT NULL, 
    preco_servico NUMBER(9,2) CONSTRAINT serv_prec_nn NOT NULL,
    duracao NUMBER(4) CONSTRAINT serv_dur_nn NOT NULL
);
CREATE TABLE peca
(
    id_peca CHAR(6) CONSTRAINT peca_id_pk PRIMARY KEY,  
    disponibilidade_peca NUMBER(8) CONSTRAINT peca_disp_nn NOT NULL, CONSTRAINT chk_disponibilidade_peca CHECK (disponibilidade_peca > 0),
    nome_peca VARCHAR(255) CONSTRAINT peca_nm_nn NOT NULL
                          CONSTRAINT peca_nm_unique UNIQUE,
    preco_peca NUMBER(9,2) CONSTRAINT peca_pc_nn NOT NULL,
    CONSTRAINT chk_preco_peca CHECK (preco_peca > 0)
);

CREATE TABLE centro_automotivo
(
    id_centro CHAR(4) CONSTRAINT centro_id_pk PRIMARY KEY,  
    nome_centro VARCHAR(155) CONSTRAINT centro_nm_nn NOT NULL,
    endereco_centro VARCHAR(255) CONSTRAINT centro_end_nn NOT NULL, 
    CONSTRAINT chk_end_centro CHECK (LENGTH(endereco_centro) > 20),
    telefone_centro CHAR(11) CONSTRAINT centro_tel_nn NOT NULL,
    horario_funcionamento VARCHAR(70) CONSTRAINT centro_hr_func_nn NOT NULL 
);


CREATE TABLE funcionario
(
    matricula_func CHAR(6) CONSTRAINT func_matric_pk PRIMARY KEY,
    nome_func VARCHAR(70) CONSTRAINT func_nm_nn NOT NULL,
    horario_trabalho VARCHAR(70) CONSTRAINT func_hr_nn NOT NULL,
    disponibilidade_func CHAR(1) CONSTRAINT func_disp_nn NOT NULL CHECK (disponibilidade_func IN ('S', 'N')),
    centro_automotivo_id_centro CHAR(4) CONSTRAINT func_id_centro_fk REFERENCES centro_automotivo(id_centro) ON DELETE SET NULL, 
    cargo_id_cargo CHAR(4) CONSTRAINT func_id_cargo_fk REFERENCES cargo(id_cargo) ON DELETE SET NULL
);

CREATE TABLE cargo
(
    id_cargo CHAR(4) CONSTRAINT cargo_id_pk PRIMARY KEY,
    nome_cargo VARCHAR(50) CONSTRAINT cargo_nm_nn NOT NULL,
    area_cargo VARCHAR(50) CONSTRAINT cargo_area_nn NOT NULL,
    descricao_cargo VARCHAR(255) CONSTRAINT cargo_desc_nn NOT NULL,
    salario_cargo NUMBER(9,2) CONSTRAINT cargo_sal_nn NOT NULL
                      CONSTRAINT cargo_sal_unique UNIQUE,
    CONSTRAINT chk_salario_cargo CHECK (salario_cargo > 0)
);

CREATE TABLE diagnostico 
(
    id_diagnostico CHAR(36) CONSTRAINT diag_id_pk PRIMARY KEY,
    descricao_sintomas VARCHAR(255) CONSTRAINT diag_desc_nn NOT NULL,
    categoria_problema VARCHAR(50) CONSTRAINT diag_cat_nn NOT NULL,
    solucao VARCHAR(255) CONSTRAINT diag_sol_nn NOT NULL
                      CONSTRAINT diag_sol_unique UNIQUE,
    status_diagnostico VARCHAR(50) CONSTRAINT diag_stt_nn NOT NULL,
    CONSTRAINT chk_status_diagnostico CHECK (status_diagnostico IN ('EM ANÁLISE', 'ANALISADO')),
    veiculo_placa CHAR(7) CONSTRAINT diag_veiculo_fk REFERENCES veiculo(placa) ON DELETE CASCADE,
    orcamento_id_orcamento CHAR(36) CONSTRAINT diag_orcamento_fk REFERENCES orcamento(id_orcamento) ON DELETE CASCADE, 
    servico_id_servico CHAR(6) CONSTRAINT diag_servico_fk REFERENCES servico(id_servico) ON DELETE CASCADE
);

CREATE TABLE agendamento
(
    id_agendamento CHAR(36) CONSTRAINT agend_id_pk PRIMARY KEY,
    data_agendamento DATE CONSTRAINT agend_dt_nn NOT NULL,
    horario_agendamento CHAR(5) CONSTRAINT agend_hr_nn NOT NULL,
    descricao_agendamento VARCHAR(255) CONSTRAINT agend_desc_nn NOT NULL,
    servico_id_servico CHAR(6) CONSTRAINT agend_id_serv_fk REFERENCES servico(id_servico) ON DELETE CASCADE,
    centro_automotivo_id_centro CHAR(4) CONSTRAINT agend_id_centro_fk REFERENCES centro_automotivo(id_centro) ON DELETE CASCADE,  
    veiculo_placa CHAR(7) CONSTRAINT agend_veiculo_fk REFERENCES veiculo(placa) ON DELETE CASCADE
);
 
CREATE TABLE oferece 
(
    servico_id_servico CHAR(6),
    centro_automotivo_id_centro CHAR(4),
    CONSTRAINT oferece_pk PRIMARY KEY (servico_id_servico, centro_automotivo_id_centro),
    CONSTRAINT oferece_servico_fk FOREIGN KEY (servico_id_servico) REFERENCES servico(id_servico) ON DELETE CASCADE,
    CONSTRAINT oferece_id_centro_fk FOREIGN KEY (centro_automotivo_id_centro) REFERENCES centro_automotivo(id_centro) ON DELETE CASCADE 
);
 
CREATE TABLE fornece
(
    peca_id_peca CHAR(6),
    servico_id_servico CHAR(6),
    CONSTRAINT fornece_pk PRIMARY KEY (peca_id_peca, servico_id_servico),
    CONSTRAINT fornece_peca_fk FOREIGN KEY (peca_id_peca) REFERENCES peca(id_peca) ON DELETE CASCADE, 
    CONSTRAINT fornece_servico_fk FOREIGN KEY (servico_id_servico) REFERENCES servico(id_servico) ON DELETE CASCADE
);

