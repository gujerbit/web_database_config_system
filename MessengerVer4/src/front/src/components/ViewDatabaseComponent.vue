<template>
  <div class="container">
    <div class="show">
      <div class="db">
        <p>DATABASE</p>
        <div class="db-list">
          <router-link
            @click="showTable($event, value)"
            to=""
            class="db-name"
            v-for="value in database"
            :key="value"
            >{{ value }}</router-link
          >
        </div>
      </div>
      <div class="table">
        <p>TABLE</p>
        <div class="table-list">
          <router-link
            @click="selectTable($event, value)"
            to=""
            v-for="value in table"
            :key="value"
            >{{ value }}</router-link
          >
        </div>
      </div>
    </div>
    <view-workspace-component v-if="currentTable !== ''" :content="currentTableContent" />
  </div>
</template>

<script>
import viewWorkspaceComponent from "./ViewWorkspaceComponent.vue";

export default {
  data() {
    return {
      database: [], //전체 데이터베이스
      table: [], //해당 데이터베이스의 전체 테이블
      currentTable: "", //현재 선택된 테이블
      selectedDB: '', //현재 선택된 데이터베이스
      selectedTable: '', //이전에 선택된 테이블
      currentTableContent: [], //현재 선택된 테이블의 모든 컬럼 및 데이터
    };
  },
  async mounted() {
    let header = {
      "jwt-auth-token": this.storage.getItem("jwt-auth-token"),
    };
    let { data } = await this.axios.post(
      "/view/view_database",
      { param: "" },
      { headers: header }
    );
    this.database = data;
  },
  methods: {
    async showTable(event, db) {
        if(this.selectedDB !== '') {
            this.selectedDB.style.color = '';
            this.selectedDB.style.backgroundColor = '';
        }

        event.currentTarget.style.color = '#ffffff';
        event.currentTarget.style.backgroundColor = '#000000';
        this.selectedTable = '';
        this.currentTable = '';
        this.selectedDB = event.currentTarget;

        let { data } = await this.axios.post(`/view/view_table`,
            {dbName: db},
            {headers: {
                "jwt-auth-token": this.storage.getItem("jwt-auth-token")
            }});
        this.table = data;

        let { data:result } = await this.axios.post('/view/use_database', {}, {
            headers: {
              'jwt-auth-token': this.storage.getItem('jwt-auth-token')
            }});
        console.log(result);
    },
    async selectTable(event, table) {
        if(this.selectedTable !== '') {
            this.selectedTable.style.color = '';
            this.selectedTable.style.backgroundColor = '';
        }

        event.currentTarget.style.color = '#ffffff';
        event.currentTarget.style.backgroundColor = '#000000';
        this.selectedTable = event.currentTarget;
        this.currentTable = table;

        let { data } = await this.axios.post('/view/view_table_content', { table: this.currentTable }, { headers: {
            'jwt-auth-token': this.storage.getItem('jwt-auth-token')
        }});
        this.currentTableContent = data;
    },
  },
  components: {
    viewWorkspaceComponent,
  },
};
</script>

<style scoped>
.container {
  width: 85%;
  display: grid;
  grid-template-columns: 300px 800px;
  gap: 10px;
  margin: 1%;
}

.show {
  display: grid;
  grid-template-rows: repeat(2, 1fr);
  gap: 5px;
  width: 300px;
  height: 300px;
}

.db, .table {
  border: 2px solid #000000;
  width: 300px;
  height: 300px;
  overflow: auto;
}

.db-list, .table-list {
  display: grid;
  width: 100%;
  height: 100%;
}

a {
  color: #000000;
}

a:hover {
  background-color: #000000;
  color: #ffffff;
  transition: all 0.7s;
}

p {
  font-size: 1.5rem;
  text-align: center;
}

.select {
    background-color: #000000;
    color: #ffffff;
}
</style>