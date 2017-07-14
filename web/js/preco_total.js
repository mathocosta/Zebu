(function () {
  const precoTotal = document.getElementById('preco-final-label')
  const todosPrecos = document.getElementsByClassName('preco-label')
  let total = 0

  for (let i = 0; i < todosPrecos.length; i++) {
    total += parseFloat(todosPrecos[i].innerHTML.replace('$', ''))
  }

  precoTotal.innerHTML = `$${total.toFixed(2)}`
})()
