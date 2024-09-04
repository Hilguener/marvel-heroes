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
<img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805671-a9591b33-e9bb-4061-ba4f-53a072824efa.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0NDI4OTgsIm5iZiI6MTcyNTQ0MjU5OCwicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU2NzEtYTk1OTFiMzMtZTliYi00MDYxLWJhNGYtNTNhMDcyODI0ZWZhLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDA5MzYzOFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWUzNmVhNmYxMzgzM2JkMmMxY2U2NDc1YmU2YjZmMTVhYjFmMmM5ZDVhYzIzYWI0YTZmZGNkODZjNjEyNmE2ZjcmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.mQaQp__A5M697P7_gdR_GdzOubxRYEWcU2umTyhX7ik"/> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805719-f408d15f-0edb-4b93-8aef-7535355770be.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0NDMwNDcsIm5iZiI6MTcyNTQ0Mjc0NywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU3MTktZjQwOGQxNWYtMGVkYi00YjkzLThhZWYtNzUzNTM1NTc3MGJlLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDA5MzkwN1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTI5MTMzNTIyOTBiY2E1NmY0OTIxNzhiZTE3Y2I5ZDlkODFhMjI4Y2NiODMzNWMyNDkyNjM5NjUwZjNiNmI3NWEmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.B_HFvSxvEPMh6bKaTxjb4cjUPDP0Op-unBtnc94KjRI"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805685-8bec8781-9b3f-4a8a-961b-c77c2496fa7f.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0NDMwNDcsIm5iZiI6MTcyNTQ0Mjc0NywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU2ODUtOGJlYzg3ODEtOWIzZi00YThhLTk2MWItYzc3YzI0OTZmYTdmLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDA5MzkwN1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTE5NTI0YzE5MDQyZGQ5NTAyMWI4ODdlYTk1YjUxMjAyNTY4NjY5ZWY5OTQ4MDMwNTRkOGI5MGE5ZjAxZDZmNDcmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.HVurSOmgYQ-jYfa16VSuKP9C_gEoaE--D-vFc466Q6c"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805782-029c480f-281c-44a7-9e05-10c4dd40d56e.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0NDMwNDcsIm5iZiI6MTcyNTQ0Mjc0NywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU3ODItMDI5YzQ4MGYtMjgxYy00NGE3LTllMDUtMTBjNGRkNDBkNTZlLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDA5MzkwN1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTRlNjAzMDQ0YzQwZTkzZjBlZDQyNWQyY2ZmNTVkMGE2YTM5NThlYmJmMDZlY2VlMjUyY2RhYmJjYmJhN2M2YzgmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.7OLx3c6PhsYsBB6zxNtQy1_GyhOcihEzWIAXJM43tdQ"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805795-7812115f-ce87-460d-8c5b-4b64ba2ad835.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjUzMjM5MTUsIm5iZiI6MTcyNTMyMzYxNSwicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU3OTUtNzgxMjExNWYtY2U4Ny00NjBkLThjNWItNGI2NGJhMmFkODM1LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDMlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTAzVDAwMzMzNVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWI0ZDFjMTI1ZmFkZWEzYWYyZWRjNWVhMDhkZmMyODcxYTM5Yzk5ZjViNjZhNTI0ZTliMzBlYzg1ZTJiNmQzMmQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.Nm-jPQ1LZPN2w4snthzAiL0r4LVoemlyOVGbwmq3yn4"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805795-7812115f-ce87-460d-8c5b-4b64ba2ad835.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjU0NDMwNDcsIm5iZiI6MTcyNTQ0Mjc0NywicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU3OTUtNzgxMjExNWYtY2U4Ny00NjBkLThjNWItNGI2NGJhMmFkODM1LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTA0VDA5MzkwN1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWMzYTA5ZmQ5OTZhN2QwZDgxODFlN2M3MmIzNDliNGUxODE0NTFhNGY2MmQwYThhZGY0ZGM4OTAwZWMwNTcwMzgmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.DQWO-Yu-V3prX0l4hJs9fgJWOkjQoUzZXO1sTda53I8"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805904-ad3af3ea-ac3d-4135-beb6-4a40efbad37d.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjUzMjM5MTUsIm5iZiI6MTcyNTMyMzYxNSwicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU5MDQtYWQzYWYzZWEtYWMzZC00MTM1LWJlYjYtNGE0MGVmYmFkMzdkLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDMlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTAzVDAwMzMzNVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTQ0OGM1YzJmYzI5YzE2Mzk5YTBjOTlkMmJiNGYyYjYxODBmMzVlZTRjZTg3YjFkMWY1NzU5ZjhlYzQ0MDU2OTYmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.5qAQ9SpXaXkt62aIvTOeSkDjhxFRM7JkWNq50G0Dfao"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805926-2f89b424-b775-42c6-b0ba-3342292accf5.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjUzMjM5MTUsIm5iZiI6MTcyNTMyMzYxNSwicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU5MjYtMmY4OWI0MjQtYjc3NS00MmM2LWIwYmEtMzM0MjI5MmFjY2Y1LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDMlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTAzVDAwMzMzNVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTc5MzQ2Mjg1NDIwYjA0N2Y4MmY0MDU5MzIzNzkwMjIwMDhkMjllYjQ3ZDg1MjhkZTY4ZmZhMzNkOTZkOTM1MzQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.Ku06D22unBCRbx3azxtJ0P6I1QYMt8e6NTfvpWt6pQw"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805939-3b3beee2-4144-4e2e-8404-35780f0914e8.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjUzMjM5MTUsIm5iZiI6MTcyNTMyMzYxNSwicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU5MzktM2IzYmVlZTItNDE0NC00ZTJlLTg0MDQtMzU3ODBmMDkxNGU4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDMlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTAzVDAwMzMzNVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTIyNGI0NzU4YTg2MTc0MTQwOWEwMGE0M2NmYmU1MmEwMjUwM2NkNjFhNmUwYjE5NDQwYzE4MDU4NGZiZGU0ZWYmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.rLiU8mhbajS5-BL4hReIjadEbMuIKeB9Zgik5vaddDM">  <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363806411-4888d6ca-8bac-4b82-8d5c-a9573beb3e24.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjUzNTYxMzIsIm5iZiI6MTcyNTM1NTgzMiwicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDY0MTEtNDg4OGQ2Y2EtOGJhYy00YjgyLThkNWMtYTk1NzNiZWIzZTI0LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDMlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTAzVDA5MzAzMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTJjOTY5OGZlNjcyNzRmNzA4NDJhZTgwODc1MzE1NjUxMWZmMTFhMWY2MzkzMzU5NTNjNmIyM2YxNzMxY2JlNmQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.DZJIyd_GB8zpb7wpsWIspBz6ylFmqw9gkHfMAk3pags"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363806418-919995a8-a951-4c20-898c-503c8c98b551.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjUzNTYxMzIsIm5iZiI6MTcyNTM1NTgzMiwicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDY0MTgtOTE5OTk1YTgtYTk1MS00YzIwLTg5OGMtNTAzYzhjOThiNTUxLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDMlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTAzVDA5MzAzMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTVjNzY2YmQyZTBmMDQ0MzY4NjE4ODVhMTFmOGJjNzU1ZDI4MTk3ODFkYTFjNzA4N2FiYWQyOTllNDYxYmM3YjEmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.xUG2HN_M5Bkd7X4Tqgnj1NR1BK5X-OxhV2vM8WK9f3g"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363806099-1502b59c-2f0f-4513-8c6c-993ca63c0b8c.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjUzNTYxMzIsIm5iZiI6MTcyNTM1NTgzMiwicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDYwOTktMTUwMmI1OWMtMmYwZi00NTEzLThjNmMtOTkzY2E2M2MwYjhjLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDMlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTAzVDA5MzAzMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWJkYzdkNGNhMDNmYTU4ZWQ3Zjk2ZmYzMzFhMjk1ZTEyMDllNzE3MWUxNjMzNGVjYjVhYzlhMzM5MmY2Y2VlMGUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.kzrIJqIOyDFVbCD_w3gN6QoQeQJEw4-m7BNYx-MsCiM"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363806304-8fc3f710-cdef-435f-9410-6481ce84d434.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjUzNTYxMzIsIm5iZiI6MTcyNTM1NTgzMiwicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDYzMDQtOGZjM2Y3MTAtY2RlZi00MzVmLTk0MTAtNjQ4MWNlODRkNDM0LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDMlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTAzVDA5MzAzMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTVjZmNmMTg1ZTJiZWIyOWRhMGY5OGM0ZTk2MTM0ODdiNGMxZmYyZmNhNDQ2YTA4ZTNlZTM0MGIzN2Y3MDg3YTQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.3xFrys4iJtQG8F7zWS10u4Ubt2VDI1cAERIv8p3ddTo"> <img width=24% height=auto src="https://private-user-images.githubusercontent.com/97263610/363805972-afded907-31c7-4269-b9de-da5ef3755597.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjUzNTYxMzIsIm5iZiI6MTcyNTM1NTgzMiwicGF0aCI6Ii85NzI2MzYxMC8zNjM4MDU5NzItYWZkZWQ5MDctMzFjNy00MjY5LWI5ZGUtZGE1ZWYzNzU1NTk3LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA5MDMlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwOTAzVDA5MzAzMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTJlN2YxYjdiOTEzYjQ4YTMwNmQ3NTE3NzQ2YmU5ZmUwZjkyNzVkOGRjN2IzZDUwNmMyZjc4MGI1Mjc4MDg2NGYmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.16Od5N3JFoupdQVk3zUxe3VPsF3Cev5CFUL1VSz2ujU">
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
