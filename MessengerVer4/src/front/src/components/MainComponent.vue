<template>
  <main>
      <div id="container" v-if="success <= 0">
        <p>LOGIN</p>
        <div class="input-field">
          <input type="text" class="id" placeholder="ID" @input="inputText($event)">
        </div>
        <div class="input-field">
          <input type="password" class="password" placeholder="PASSWORD" @input="inputText($event)">
        </div>
        <div class="button" @click="login()">Login</div>
      </div>
      <view-message-component :success="success" />
  </main>
</template>

<script>
import viewMessageComponent from './ViewMessageComponent.vue';

export default {
  data() {
    return {
      id: '',
      password: '',
      success: 0,
    }
  },
  async mounted() {

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
        let loginInfo = [this.id, this.password];
        let { data } = await this.axios.post(`login`, loginInfo);
        this.success = data;
      }
    }
  },
  components: {
    viewMessageComponent,
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