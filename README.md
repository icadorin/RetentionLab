# 📊 RetentionLab

**Structural Retention Analysis Engine for Long-Form Scripts**

RetentionLab é um motor analítico de retenção estrutural para roteiros long-form (YouTube).

Ele transforma texto em estrutura mensurável.

- Não prevê viralidade
- Não depende de dados proprietários
- Não usa heurística opaca

**RetentionLab mede estrutura narrativa objetiva.**

---

## 🎯 The Problem

Criadores escrevem roteiros baseados em:

- Intuição
- Experiência
- Referência subjetiva

Mas não possuem uma forma estruturada de medir:

- Força do hook
- Distribuição de tensão
- Densidade de loops narrativos
- Risco de queda estrutural

RetentionLab introduz um **modelo analítico determinístico** para resolver isso.

---

## 🧠 Core Concept

RetentionLab é um sistema híbrido:

- **LLM** → Extrai atributos estruturais
- **Engine Matemático** → Calcula score determinístico

**A LLM não decide.**  
Ela apenas extrai dados estruturados.

O score final é:

- Explicável
- Auditável
- Reproduzível
- Parametrizável

---

## 🏗 Architecture (v1.0)

### 1️⃣ Structural Segmentation
- Contagem de palavras
- Estimativa de duração
- Divisão em blocos temporais
- Indexação narrativa

### 2️⃣ LLM Structural Extraction
Execução local via **Ollama**.  
A LLM retorna JSON estruturado contendo:

- Conflito inicial
- Curiosity gap
- Open loops
- Closed loops
- Tensão por bloco
- Métricas de exposição

### 3️⃣ Deterministic Scoring Engine
Calcula os seguintes pilares:

- Structural Segmentation
- Hook Magnetism
- Loop Density
- Tension Curve
- Risk Blocks
- Loop Closure

Score final: **0–100**  
Totalmente matemático e auditável.

---

## 📈 Retention Score Breakdown (v1.0)

| Pilar | Peso | Descrição |
| :--- | :---: | :--- |
| 🧱 **Structural Segmentation** | `25` | Consistência estrutural e estabilidade de blocos |
| 🎣 **Hook Magnetism** | `20` | Força estrutural da abertura |
| 🔄 **Loop Density** | `20` | Frequência de loops narrativos |
| 📈 **Tension Curve** | `15` | Progressão estrutural da tensão |
| ⚠️ **Risk Blocks** | `10` | Identificação de zonas de baixa intensidade |
| 🎯 **Loop Closure** | `10` | Capacidade de fechar promessas narrativas |
| **Total** | **100** | **Score determinístico** |

---

## 🔍 What v1.0 Does

- Analisa roteiro textual
- Gera score estrutural (0–100)
- Identifica blocos de risco
- Detecta loops abertos e fechados
- Avalia progressão de tensão
- Produz diagnóstico automatizado

---

## ❌ What v1.0 Does NOT Do

- Não prevê retenção real do YouTube
- Não utiliza dados externos
- Não utiliza modelo treinado próprio
- Não depende de modelos proprietários pagos
- Não otimiza algoritmo de plataforma

> RetentionLab mede estrutura.  
> Não performance histórica.

---

## 🧩 Design Principles

- Engine matemático antes de predição
- LLM como extrator, não como juiz
- Estrutura antes de otimização
- Parametrização explícita
- Modularidade desde o início
- Evolução incremental

---

## 🖥 Why Local-First?

RetentionLab v1.0 foi projetado para independência operacional.

### 💻 Hardware Constraints
Desenvolvido com:
- 8GB RAM
- Sem GPU dedicada

Decisões adotadas:
- Modelos leves
- Execução sob demanda
- Sem paralelismo pesado
- Sem containerização inicial
- Simplicidade operacional foi priorizada sobre infraestrutura complexa.

### 💰 Financial Constraints
O projeto iniciou com:
- Zero investimento
- Sem API paga
- Sem dependência de créditos cloud

Estratégia adotada:
- Uso de LLM local
- Zero custo por requisição
- Independência de providers externos

---

## 📦 Tech Philosophy

RetentionLab v1.0 **não é um produto de IA.**

É um:  
**Engine estrutural determinístico assistido por LLM.**

Isso permite:
- Controle total do score
- Transparência na lógica
- Evolução modular
- Auditoria completa

---

## 🔮 Future Vision (v2.0+)

| Componente | Descrição |
| :--- | :--- |
| ⏱️ **Loop Latency Analysis** | Mede tempo entre abertura e fechamento de loops |
| 📉 **Mid-Video Sag Detection** | Detecta quedas estruturais no meio do roteiro |
| 🎚️ **Niche Calibration** | Ajusta parâmetros por nicho |
| 🤖 **Predictive Retention Modeling** | Correlaciona score com retenção real |
| 📊 **Multi-Script Benchmarking** | Comparação entre múltiplos roteiros |

---
