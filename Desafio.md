# Wishlist

## Feature
Uma das funcionalidades mais interessantes em um e-commerce
é a Wishlist, ou a lista de desejos. No e-commerce o cliente pode
realizar a busca de produtos, ou pode acessar a tela de detalhes
do produto. Em ambas as telas ele pode selecionar os produtos
de sua preferência e armazená-los na sua Wishlist. A qualquer
momento o cliente pode visualizar sua Wishlist completa, com
todos os produtos que ele selecionou em uma única tela.

## O que deve ser feito?
O objetivo é que você desenvolva um serviço HTTP resolvendo a
funcionalidade de Wishlist do cliente. Esse serviço deve atender
os seguintes requisitos:
- Adicionar um produto na Wishlist do cliente;
- Remover um produto da Wishlist do cliente;
- Consultar todos os produtos da Wishlist do cliente;
- Consultar se um determinado produto está na Wishlist do
cliente;

## Orientações
Imagine que esse serviço fará parte de uma plataforma
construída em uma arquitetura baseada em micro-serviços.
Portanto não se preocupe com a gestão das informações de
Produtos e/ou Clientes, coloque sua energia apenas no serviço da
Wishlist.
É importante estabelecer alguns limites para proteger o bom
funcionamento do ecossistema, dessa forma a Wishlist do cliente
deve possuir um limite máximo de 20 produtos.

## Premissas
Linguagem: Java 11 ou >
Framework http: Spring boot
Gerenciador de dependência: Maven ou Gradle
Banco de Dados: Qualquer noSQL
