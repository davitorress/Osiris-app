<p align="center">
<img src="https://github.com/davitorress/Osiris-app/assets/104948713/5dfe90f9-43a4-442d-b499-04a74b9bfc0a" align="center" width="400">
</p>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
![GitHub](https://img.shields.io/github/license/davitorress/Osiris-app)
![GitHub language count](https://img.shields.io/github/languages/count/davitorress/Osiris-app)
![GitHub last commit](https://img.shields.io/github/last-commit/davitorress/Osiris-app)

Osíris é um projeto de graduação em andamento que promove uma alimentação mais saudável por meio do uso de Plantas Alimentícias Não Convencionais (PANCs). Ele fornece informações de cultivo, diversas receitas e permite que os usuários criem suas próprias receitas.

___

# Guia de Execução - Aplicação Mobile e API

Este guia fornece instruções passo a passo sobre como configurar e executar a aplicação mobile e a API associada. Certifique-se de seguir todas as etapas para garantir uma execução suave do projeto.

## Requisitos Prévios

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em seu sistema:

- **IntelliJ IDEA**: A aplicação mobile é desenvolvida em Kotlin, e recomendamos o uso da IntelliJ IDEA como a IDE preferencial. Faça o download e instalação a partir do [site oficial](https://www.jetbrains.com/idea/).

- **MongoDB**: A aplicação e a API dependem do MongoDB como banco de dados. Certifique-se de ter o MongoDB instalado em sua máquina. Você pode baixá-lo em [MongoDB Download Center](https://www.mongodb.com/try/download/community).

___

## Configurando a Aplicação Mobile

1. Abra o IntelliJ IDEA.

2. Clone o repositório do projeto:

   ```bash
   git clone https://github.com/davitorress/Osiris-app.git
   ```

3. Abra o projeto na IntelliJ IDEA.

4. Aguarde a IDE sincronizar as dependências do projeto.

5. Certifique-se de ter o emulador Android configurado ou um dispositivo físico conectado.

6. Configure o Retrofit BUilder e XML de rede, para utilizar o IP de sua máquina:

  - ### Retrofit Builder
    No arquivo `RetrofitInitializer.kt`, encontre a configuração do Retrofit Builder. Substitua o endereço base pela IP da sua máquina. Por exemplo:
    ```kotlin
     private val retrofit = Retrofit.Builder()
          .baseUrl("http://SEU_IP_AQUI:8080/")
          .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
          .build()
     ```

- ### Arquivo de Configuração de Rede XML do Kotlin
  No arquivo de configuração de rede XML do Kotlin, ajuste as configurações de URL para usar o IP da sua máquina. Por exemplo:
  ```xml
  <network-security-config>
    <base-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">SEU_IP_AQUI</domain>
        <!-- ... outras configurações ... -->
    </base-config>
  </network-security-config>
  ```
  Certifique-se de substituir "SEU_IP_AQUI" pelo IP real da sua máquina.

7. Execute a aplicação mobile.

___

## Configurando a API

1. Abra o IntelliJ IDEA.

2. Clone o repositório da API:

   ```bash
   git clone https://github.com/joao-arthr/osiris.git
   ```

3. Abra o projeto na IntelliJ IDEA.

4. Aguarde a IDE sincronizar as dependências do projeto.

5. Certifique-se de ter o MongoDB em execução em sua máquina.

6. Execute a API.

___

## Observações importantes

- Certifique-se de que as portas necessárias (8080 para a API) estejam disponíveis em sua máquina.
- Certifique-se de que as credenciais do mongoDB estão corretamente configuradas. Elas estão disponíveis no arquivo `custom.properties`.

Ao seguir essas etapas, você deve ser capaz de executar tanto a aplicação mobile quanto a API em seu ambiente de desenvolvimento. Se encontrar problemas durante a execução, consulte a documentação do projeto ou entre em contato com a equipe de desenvolvimento para obter assistência.
