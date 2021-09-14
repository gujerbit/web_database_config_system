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
    <view-workspace-component v-if="currentTable !== ''" :table="currentTable" />
  </div>
</template>

<script>
import viewWorkspaceComponent from "./ViewWorkspaceComponent.vue";

export default {
  data() {
    return {
      database: [],
      table: [],
      currentTable: "",
      selectedDB: '',
      selectedTable: '',
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
            this.selectedDB.style.color = '#000000';
            this.selectedDB.style.backgroundColor = '#ffffff';
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
    },
    async selectTable(event, table) {
        if(this.selectedTable !== '') {
            this.selectedTable.style.color = '#000000';
            this.selectedTable.style.backgroundColor = '#ffffff';
        }

        event.currentTarget.style.color = '#ffffff';
        event.currentTarget.style.backgroundColor = '#000000';
        this.selectedTable = event.currentTarget;
        this.currentTable = table;
    },
  },
  components: {
    viewWorkspaceComponent,
  },
};
</script>

<style scoped>
.container {
  width: 80%;
  margin: 1%;
  display: grid;
  grid-template-columns: 1fr 2fr;
}

.show {
    display: grid;
    grid-template-rows: 1fr 1fr;
}

.container p {
  text-align: center;
  font-size: 1.5rem;
}

.db-list,
.table-list {
  max-height: 250px;
  min-height: 250px;
  max-width: 330px;
  min-width: 330px;
  display: grid;
  overflow: auto;
}

.db-list a,
.table-list a {
  color: #000000;
}

.db-list a:hover,
.table-list a:hover {
  color: #ffffff;
  background-color: #000000;
  transition: all 1s;
}

.db,
.table {
  margin: 1%;
  border: 2px solid #000000;
}

.select {
    background-color: #000000;
    color: #ffffff;
}
</style>