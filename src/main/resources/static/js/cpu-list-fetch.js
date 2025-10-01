document.addEventListener("DOMContentLoaded", initCpuList);

function initCpuList() {
  fetch('/api/cpus')
    .then(res => res.json())
    .then(renderCpuList)
	
	
    .catch(err => console.error("CPU 목록 로딩 실패:", err));
}

function renderCpuList(data) {
console.log(data);
  const table = document.querySelector('.cpulist_table_page1');
  data.forEach(cpu => {
    const row = document.createElement('tr');
    row.className = 'SearchResult_Table_Row_page1';
    row.innerHTML = `
      <td>
        <div class="total1_image">
          <div class="Module_Layout_1_1_1_1_0" style="margin-left:10px;">
            <a style="font-size:14px; color:white;">Proc</a>
          </div>
        </div>
      </td>
      <td>
        <div class="total1_type">
          <div class="total2_type">
            <a>${cpu.typeCpu}</a>
          </div>
        </div>
      </td>
      <td>
        <div class="total1_manf_image">
          <div class="total2_manf_image">
            <img src="/image/${cpu.manfCpu}.png" style="width: 100%; height:100%">
          </div>
        </div>
      </td>
      <td>
        <div class="total1_name">
          <div class="total2_name">
            <a href="/cpus/${cpu.idCpu}">${cpu.nameCpu}</a>
          </div>
        </div>
      </td>
      <td style="margin-left:-15px">
        <div class="total1_c">
          <div class="total2_c">
            <a>${cpu.coreCpu}</a>
            <a style="font-size:16px; margin-left:3px;">코어</a>
          </div>
        </div>
      </td>
      <td>
        <div class="total1_t">
          <div class="total2_t">
            <a style="font-size:16px; margin-right:5px; margin-left:-12px;">최고</a>
            <a>${cpu.maxghzCpu}</a>
            <a style="font-size:16px; margin-left:5px;">Ghz</a>
          </div>
        </div>
      </td>
    `;
    table.appendChild(row);
  });
}
