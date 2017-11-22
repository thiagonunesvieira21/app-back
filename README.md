   
## BANCO DE DADOS ###
- Postgres
    - create user "app" com senha "app"
    
### INSTALAR REDIS ###

- sudo apt-get install redis-server
- Comando para apagar todos registros do Redis: redis-cli keys '*'| xargs redis-cli del

MAC Instalação/Start/Stop

    brew install redis
    redis-server /usr/local/etc/redis.conf

### ENDEREÇO SWAGGER ###
- http://localhost:8080/swagger-ui.html

Logue com o seu nome de usuário thiago.vieira
Sua senha provisória é 3fd4b097

    
### FEITO

    posdes01.cadastral.atributo
    posdes01.cadastral.categoria
    posdes01.cadastral.deposito
    posdes01.cadastral.imagem
    posdes01.cadastral.marca_fabricante
    posdes01.cadastral.produto
    posdes01.cadastral.unidade_medida
    posdes01.cadastral.estoque
    
## Á FAZER    
    posdes01.cadastral.item_lista_compra
    posdes01.cadastral.lista_compra
    posdes01.cadastral.relacao_produto
