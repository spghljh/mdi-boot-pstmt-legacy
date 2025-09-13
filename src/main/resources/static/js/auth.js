// static/js/auth.js

document.addEventListener("DOMContentLoaded", () => {
  const token = localStorage.getItem("jwt");
  const userInfo = document.getElementById("user-info");

  if (!userInfo) return; // 해당 요소 없으면 무시

  if (token) {
    fetch("/api/user/me", {
      headers: { Authorization: `Bearer ${token}` }
    })
    .then(res => res.ok ? res.json() : Promise.reject())
    .then(data => {
      userInfo.innerHTML = `
        <span>${data.username}님</span>
        <a href="/logout">로그아웃</a>
      `;
    })
    .catch(() => {
      userInfo.innerHTML = `<a href="/login">로그인</a>`;
    });
  } else {
    userInfo.innerHTML = `<a href="/login">로그인</a>`;
  }
});
