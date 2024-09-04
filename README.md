# Marvel Heroes

Aplicativo que aproveita a <a href= "https://developer.marvel.com/docs#!/public/getCreatorCollection_get_0">Api da Marvel</a> para acessar dados sobre personagens, revistas em quadrinhos, histórias, eventos, criadores e series sobre o universo da marvel. 

## Como usar
- Clone este projeto.
- Vá até a documentação da API e retire a sua PUBLIC API KEY e PRIVATE API KEY
- No arquivo local.properties, crie essas duas contantes e cole os respectivos valores nelas.
<img src="https://private-user-images.githubusercontent.com/97263610/364168835-7d87a0c9-0a50-47e2-b6f1-9fa80a4de8f7.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0NDMyMjksIm5iZiI6MTcyNTQ0MjkyOSwicGF0aCI6Ii85NzI2MzYxMC8zNjQxNjg4MzUtN2Q4N2EwYzktMGE1MC00N2UyLWI2ZjEtOWZhODBhNGRlOGY3LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDA5NDIwOVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWVlZjJiZTBjZTJiYjJjZjIzMGJmODA0NDE0ZTUyNmI3NmU4MmQ0N2IzYzFmZDAyNTBhZjNkN2FlMmNkY2IxMTkmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.wL5uSK0NR7pK7JXN_rQlytLDu_gCGjH97hkdW8kiTjE">

## Features
<ul>
 <li>Personagens: Explore uma lista de personagens com funcionalidade de pesquisa.
 <li>Quadrinhos: Acesse uma lista de quadrinhos com opção de pesquisa.
 <li>Eventos: Visualize uma lista de eventos com recurso de pesquisa.
 <li>Histórias: Consulte uma lista de histórias com funcionalidade de pesquisa.
 <li>Criadores: Descubra criadores através de uma lista pesquisável.
 <li>Séries: Explore uma lista de séries com opção de pesquisa.
</ul>

## Screenshots
<img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805671-a9591b33-e9bb-4061-ba4f-53a072824efa.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0NDI4OTgsIm5iZiI6MTcyNTQ0MjU5OCwicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU2NzEtYTk1OTFiMzMtZTliYi00MDYxLWJhNGYtNTNhMDcyODI0ZWZhLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDA5MzYzOFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWUzNmVhNmYxMzgzM2JkMmMxY2U2NDc1YmU2YjZmMTVhYjFmMmM5ZDVhYzIzYWI0YTZmZGNkODZjNjEyNmE2ZjcmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.mQaQp__A5M697P7_gdR_GdzOubxRYEWcU2umTyhX7ik"/> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805719-f408d15f-0edb-4b93-8aef-7535355770be.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0NDMwNDcsIm5iZiI6MTcyNTQ0Mjc0NywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU3MTktZjQwOGQxNWYtMGVkYi00YjkzLThhZWYtNzUzNTM1NTc3MGJlLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDA5MzkwN1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTI5MTMzNTIyOTBiY2E1NmY0OTIxNzhiZTE3Y2I5ZDlkODFhMjI4Y2NiODMzNWMyNDkyNjM5NjUwZjNiNmI3NWEmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.B_HFvSxvEPMh6bKaTxjb4cjUPDP0Op-unBtnc94KjRI"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805685-8bec8781-9b3f-4a8a-961b-c77c2496fa7f.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0NDMwNDcsIm5iZiI6MTcyNTQ0Mjc0NywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU2ODUtOGJlYzg3ODEtOWIzZi00YThhLTk2MWItYzc3YzI0OTZmYTdmLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDA5MzkwN1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTE5NTI0YzE5MDQyZGQ5NTAyMWI4ODdlYTk1YjUxMjAyNTY4NjY5ZWY5OTQ4MDMwNTRkOGI5MGE5ZjAxZDZmNDcmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.HVurSOmgYQ-jYfa16VSuKP9C_gEoaE--D-vFc466Q6c"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805782-029c480f-281c-44a7-9e05-10c4dd40d56e.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0NDMwNDcsIm5iZiI6MTcyNTQ0Mjc0NywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU3ODItMDI5YzQ4MGYtMjgxYy00NGE3LTllMDUtMTBjNGRkNDBkNTZlLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDA5MzkwN1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTRlNjAzMDQ0YzQwZTkzZjBlZDQyNWQyY2ZmNTVkMGE2YTM5NThlYmJmMDZlY2VlMjUyY2RhYmJjYmJhN2M2YzgmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.7OLx3c6PhsYsBB6zxNtQy1_GyhOcihEzWIAXJM43tdQ">  <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805877-75b8a6ec-3cd3-414c-b200-b885223cfc36.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0ODU2OTMsIm5iZiI6MTcyNTQ4NTM5MywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU4NzctNzViOGE2ZWMtM2NkMy00MTRjLWIyMDAtYjg4NTIyM2NmYzM2LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDIxMjk1M1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTllZTU1ZTUyZGNiOTFlZDk5YzQzNTE5NGIwYzg2YzRmOTQwMGZjM2IyOTJiMzc5MDQzN2ZkZjIxYzEyNWZiMTAmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.x3pw1X83pg0-opZNRAVWIpkg8XZmjT3nQBRclsLGOZc"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805904-ad3af3ea-ac3d-4135-beb6-4a40efbad37d.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0ODU2OTMsIm5iZiI6MTcyNTQ4NTM5MywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU5MDQtYWQzYWYzZWEtYWMzZC00MTM1LWJlYjYtNGE0MGVmYmFkMzdkLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDIxMjk1M1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTk5NGMzOWQyOTQ4ZmM2ZjhlY2RjNGFkMGY5M2RhYzM5OWI3MWE1NjY1Mzc4ZThhZDNhZDhlMDZmMzlmYzkyNWQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.hCU91j8emBqfncHgdA5pS4hZPxpziRTBVPBGLS8q1ZA"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805926-2f89b424-b775-42c6-b0ba-3342292accf5.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0ODU2OTMsIm5iZiI6MTcyNTQ4NTM5MywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU5MjYtMmY4OWI0MjQtYjc3NS00MmM2LWIwYmEtMzM0MjI5MmFjY2Y1LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDIxMjk1M1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWQ4ZGYyMTBlNTllOGEwYmIwM2JmMTBkYWQ1NDg0N2IxZDg5OTkyOTA4NjEwMTY1NWIzY2MxOGNmNjk4NTI0N2MmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.Xfdkn0eD0HdBzmYYG8tV-UWsbSXMWrK8uMDsPxQFx0w"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805939-3b3beee2-4144-4e2e-8404-35780f0914e8.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0ODU2OTMsIm5iZiI6MTcyNTQ4NTM5MywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU5MzktM2IzYmVlZTItNDE0NC00ZTJlLTg0MDQtMzU3ODBmMDkxNGU4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDIxMjk1M1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTNhY2MwNWM1NzJkYTUwYzE3Zjc3Y2ZjOTUyOGNmYTNkZWNjN2IxOWE2NTk4YTRjNzFlN2U3MzIzYTUwYzhlM2ImWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.v1yimsSsg8G9aQsZZtbasGEzjfWn-ZbIu8Iu9CadJgU">  <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805972-afded907-31c7-4269-b9de-da5ef3755597.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0ODU2OTMsIm5iZiI6MTcyNTQ4NTM5MywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU5NzItYWZkZWQ5MDctMzFjNy00MjY5LWI5ZGUtZGE1ZWYzNzU1NTk3LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDIxMjk1M1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTUxNTAwMTVjZWVjNGYxNzFhZDhlN2NmMmZhMzhlM2I4MDg3YzU2NWUwYjI5MTcyYjI5YWQ5ZjdhYzU3Yjc1ODcmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.zIlgXvFh0cZTYKsPtd0rCXqy2WU6f5-DlUaY2tp5BP4"> 
## Tecnologias Utilizadas
<ul>
  <li> Requisições HTTP: Retrofit2
  <li> Arquitetura: MVVM (Model-View-ViewModel) para uma estrutura de código organizada e escalável.
  <li> Injeção de dependência: Koin, facilitando a injeção de dependências de forma simples e eficiente.
  <li> Framework de Interface do Usuário: Jetpack Compose para construção de interfaces modernas e reativas.
  <li> Navegação: Navigation Compose para navegação entre telas.
  <li> Paginação: Paging 3 para carregamento eficiente de listas paginadas.
  <li> Cache de Imagens: Glide para carregamento e cache de imagens de maneira eficiente.
  <li> Ferramenta de Análise Estática de Código: Kotlin Linter (lintKotlin)
</ul>

## Comandos
- `gradlew build`: Compilação do aplicativo.
- `gradlew lintKotlin`: Verificação de conformidade com as regras de codificação Kotlin.
