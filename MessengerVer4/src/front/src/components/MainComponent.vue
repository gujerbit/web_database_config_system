<template>
  <main>
      <div id="container">
        <p>LOGIN</p>
        <div class="input-field">
          <input type="text" class="id" placeholder="ID" @input="inputText($event)">
        </div>
        <div class="input-field">
          <input type="password" class="password" placeholder="PASSWORD" @input="inputText($event)">
        </div>
        <div class="button" @click="login()">Login</div>
      </div>
  </main>
</template>

<script>
export default {
  data() {
    return {
      id: '',
      password: ''
    }
  },
  async mounted() {
    if(this.storage.getItem('jwt-auth-token') !== null) {
      location.href = '/view';
    }
  },
  methods: {
    inputText(event) {
      let value = event.currentTarget.value;

      if(event.currentTarget.className.includes('id')) {
        this.id = value;
      } else if(event.currentTarget.className.includes('password')) {
        this.password = value;
      }

      if(value.length > 0) {
        event.currentTarget.classList.add('non-empty');
      } else {
        event.currentTarget.classList.remove('non-empty');
      }
    },
    async login() {
      if(this.id !== '' && this.password !== '') {
        this.storage.setItem("jwt-auth-token", "");
        this.storage.setItem("id", "");

        let loginInfo = [this.id, this.password];
        this.axios.post(`/user/login`, loginInfo).then(res => {
          if(res.data.status) {
            console.log('login success');
            this.storage.setItem("jwt-auth-token", res.headers["jwt-auth-token"]);
            this.storage.setItem("id", res.data.data.id);
            location.href = '/view';
          } else {
            console.log('login faild');
          }
        });
      }
    }
  }
}
</script>

<style scoped>
main {
  width: 100%;
}

#container {
  width: 50%;
  padding: 3% 0;
  margin: 0 auto;
  margin-top: 10%;
  border: 2px solid #000000;
}

p {
  text-align: center;
  font-size: 2rem;
}

.input-field {
  display: flex;
  justify-content: center;
  margin: 5% 0;
}

input {
  width: 40%;
  margin: 1%;
  padding: 1.5%;
  border-color: #000000;
  border-radius: 50px;
  outline: 0;
}

input:hover, input:focus, .button:hover, .non-empty {
  background-color: #000000;
  color: #ffffff;
  transition: all 1s;
}

.button {
  width: 30%;
  padding: 1%;
  margin: 1% auto;
  border: 2px solid #000000;
  border-radius: 50px;
  text-align: center;
}
</style>