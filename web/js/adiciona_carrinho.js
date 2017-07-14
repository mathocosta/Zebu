(function () {
  const allCarrinhoBtn = document.getElementsByClassName('add-carrinho-btn')

  function adicionaCarrinho(codigo) {
    let req = new XMLHttpRequest()
    req.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200)
        alert('Produto adicionado ao carrinho!')
    }
    req.open('GET', `/Zebu/adicionacarrinho?codigo=${encodeURIComponent(codigo)}`)
    req.send()
  }

  for (let i = 0; i < allCarrinhoBtn.length; i++) {
    allCarrinhoBtn[i].addEventListener('click', function (event) {
      let codigo = this.getAttribute('data-prod-cod')
      codigo ? adicionaCarrinho(codigo) : console.error("Não foi pego o codigo")
    })
  }
})()


