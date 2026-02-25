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

RetentionLab introduz um modelo analítico para resolver isso.

---

## 🧠 Core Concept

RetentionLab é um sistema híbrido:

- **LLM** → Extrai atributos estruturais
- **Engine matemático** → Calcula score determinístico

**A LLM não decide.**  
Ela apenas extrai dados estruturados.

O score final é:

- Explicável
- Auditável
- Reproduzível

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

- Conflito
- Curiosity gap
- Open loops
- Tension per block
- Métricas de exposição

### 3️⃣ Deterministic Scoring Engine
Calcula os seguintes pilares:

- Hook Magnetism
- Loop Density
- Tension Curve
- Risk Blocks
- Loop Closure

Score final: **0–100**  
Totalmente matemático e auditável.

---

## 📈 Retention Score Breakdown

| Pilar | Peso | Função |
| :--- | :---: | :--- |
| 🎣 **Hook Magnetism** | `20` | Mede a força da abertura: conflito imediato, concretude e quebra de padrão |
| 🔄 **Loop Density** | `20` | Avalia a frequência e qualidade dos loops abertos ao longo do roteiro |
| 📈 **Tension Curve** | `20` | Analisa a progressão da tensão e existência de pico narrativo |
| ⚠️ **Risk Blocks** | `20` | Identifica zonas de exposição longa e risco de abandono |
| 🎯 **Loop Closure** | `20` | Verifica se os loops abertos são fechados de forma satisfatória |

---

## 🔍 What v1.0 Does

- Analisa roteiro textual
- Gera score estrutural
- Identifica blocos de risco
- Detecta loops abertos e fechados
- Produz diagnóstico automatizado

---

## ❌ What v1.0 Does NOT Do

- Não prevê retenção real do YouTube
- Não usa dados externos
- Não utiliza modelo treinado próprio
- Não depende de modelos proprietários

---

## 🚀 Design Principles

- Engine matemático antes de predição
- LLM como extrator, não como juiz
- Estrutura antes de otimização
- Modularidade desde o início
- Evolução incremental

---

## 🧩 Why Local-First?

RetentionLab v1.0.

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

---

## 🔮 Future Vision (v2.0+)

| Componente | Descrição |
| :--- | :--- |
| ⏱️ **Loop Latency Analysis** | Mede o tempo entre abertura e fechamento de cada loop |
| 📉 **Mid-Video Sag Detection** | Identifica quedas de tensão no meio do roteiro |
| 🎚️ **Niche Calibration** | Ajusta parâmetros por nicho (educação, entretenimento, etc) |
| 🤖 **Predictive Retention Modeling** | Correlaciona score com dados reais de retenção |
| 📊 **Multi-Script Benchmarking** | Compara roteiros lado a lado |

---
