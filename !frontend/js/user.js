window.userId = parseInt(window.location.search.replace(/\D+/g,""));

// Добавление ссылки "Новое объявление"
// Была не доступна для инициализации сразу, т. к. не была проинициализирована переменная userId
const addLink = () =>
{
  document.querySelector("#link").href = "newAd.html?id=" + userId;
}
addLink();
//-----------------------------------------Отображение пользователя-----------------------------------------
// Показ авы и баланса
const renderUser = user => `
  <div class="user">
    <div class="user_pic-mini">
      <img src=${user.PIC_URL}>
      <div class="user_balance">
        <img src="/Currency.png">
        <span>${user.BALANCE}</span>
      </div>
    </div>
  </div>
`;

// Вывод пользователя
const getSelectedUser = userId => {
  createRequest({ path: "USERS/" + userId, method: "GET" }) // ПУТЬ И ЗАПРОС
    .then(response => {
      document.querySelector("#selected-user").innerHTML = renderUser(response);
    })
    .catch(err => {
      document.querySelector("#selected-user").innerHTML =
        "Не удалось отобразить пользователя";
    });
};

getSelectedUser(userId);
