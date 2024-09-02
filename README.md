# Marvel Heroes

Aplicativo que aproveita a <a href= "https://developer.marvel.com/docs#!/public/getCreatorCollection_get_0">Api da Marvel</a> para acessar dados sobre personagens, revistas em quadrinhos, histórias, eventos, criadores e series sobre o universo da marvel.

## Features
<ul>
 <li>Personagens: Explore uma lista de personagens com funcionalidade de pesquisa.
 <li>Quadrinhos: Acesse uma lista de quadrinhos com opção de pesquisa.
 <li>Eventos: Visualize uma lista de eventos com recurso de pesquisa.
 <li>Histórias: Consulte uma lista de histórias com funcionalidade de pesquisa.
 <li>Criadores: Descubra criadores através de uma lista pesquisável.
 <li>Séries: Explore uma lista de séries com opção de pesquisa.
</ul>

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
