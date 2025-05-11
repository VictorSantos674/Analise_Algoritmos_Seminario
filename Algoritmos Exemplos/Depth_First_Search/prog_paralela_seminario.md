# Arquitetura Apache Spark - Anotações Detalhadas

## 🔥 3) Arquitetura Spark

### 🔹 Cluster Mode: Driver, Executors, Worker Nodes

* **Driver Program**:

  * Aplicação principal do Spark.
  * Responsável por:

    * Criar SparkContext/SparkSession.
    * Construir o DAG.
    * Dividir em stages.
    * Enviar tasks aos executors.

* **Cluster Manager**:

  * Aloca recursos do cluster.
  * Tipos: YARN, Mesos, Kubernetes, Spark Standalone.

* **Worker Nodes**:

  * Máquinas que executam as tarefas.

* **Executors**:

  * Processos que executam as tasks e armazenam dados em cache.

---

### 🔹 DAG e Ciclo de Vida de uma Aplicação Spark 

* **DAG (Directed Acyclic Graph)**:

  * Representa transformações aplicadas aos dados.
  * Sem ciclos; garante fim da execução.

* **Fases**:

  1. Criação do SparkContext.
  2. DAG de transformações (lazy evaluation).
  3. Ação → gera plano de execução.
  4. DAG → stages → tasks.
  5. Executors executam as tasks.

---

### 🔹 Comparativo com Outras Abordagens Paralelas 

| Característica         | Hadoop MapReduce | Apache Spark             |
| ---------------------- | ---------------- | ------------------------ |
| Execução               | Disco            | Memória (RAM)            |
| Velocidade             | Lenta            | Muito rápida             |
| API                    | Limitada         | Rica (RDD, DataFrame...) |
| Suporte a ML/Streaming | Limitado         | Completo                 |
| Tolerância a falhas    | Alta             | Moderada                 |

---

### ✅ Divisão em Stages e Tasks 

* **Stage**:

  * Fase com tasks que não exigem shuffle.

* **Task**:

  * Unidade mínima de execução.
  * Uma por partição.

* **Processo**:

  1. DAG é analisado.
  2. Dividido em stages.
  3. Cada stage em tasks.
  4. Tasks enviadas aos executors.

#### Exemplo:

```python
rdd = sc.textFile("arquivo.txt")
rdd2 = rdd.map(lambda x: x.split(","))
rdd3 = rdd2.groupByKey()
rdd4 = rdd3.count()
```

* `groupByKey()` causa shuffle → gera dois stages.

---

### ✅ Shuffle, Narrow vs Wide Transformations 

#### Narrow Transformations (sem shuffle):

* Ex: `map()`, `filter()`
* Partição de entrada → uma de saída.
* Mais eficientes.

#### Wide Transformations (com shuffle):

* Ex: `groupByKey()`, `join()`
* Partição de saída depende de várias de entrada.
* Gera troca de dados entre nós.

#### Shuffle:

* Redistribuição de dados.
* Envolve:

  * Escrita/leitura em disco,
  * Tráfego de rede.

#### Dicas:

* Prefira `reduceByKey()` a `groupByKey()`.
* Use `broadcast()` para joins otimizados.

---

### 📊 Exemplo Completo

```python
rdd = sc.textFile("dados.csv")
rdd1 = rdd.map(lambda x: x.split(","))
rdd2 = rdd1.map(lambda x: (x[0], int(x[1])))
rdd3 = rdd2.reduceByKey(lambda a, b: a + b)
```

* `map()` → narrow.
* `reduceByKey()` → wide (shuffle).
* Dois stages são gerados.
