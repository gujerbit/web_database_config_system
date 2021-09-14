<template>
  <main>
      <div class="user-info">
        <p class="user-id">{{storage.getItem('id')}}님 접속중</p>
        <div class="logout-btn" @click="logout()">로그아웃</div>
      </div>
      <view-database-component />
  </main>
</template>

<script>
import viewDatabaseComponent from './ViewDatabaseComponent.vue';

export default {
    data() {
        return {

        }
    },
    mounted() {
        if(this.storage.getItem('jwt-auth-token') === null) {
            location.href = '/';
        }
    },
    methods: {
        logout() {
            this.storage.removeItem('jwt-auth-token');
            this.storage.removeItem('id');
            location.href = '/';
        }
    },
    components: {
        viewDatabaseComponent
    }
}
</script>

<style scoped>
main {
    width: 100%;
}

.user-info {
    position: absolute;
    right: 0;
    top: 0;
    transform: translate(-10%, 10%);
    text-align: center;
}

.user-id, .logout-btn {
    border: 1px solid #000000;
    padding: 10px;
    margin: 5px;
}

.logout-btn {
    cursor: pointer;
}

.logout-btn:hover {
    background-color: #000000;
    color: #ffffff;
    transition: all 1s;
}
</style>