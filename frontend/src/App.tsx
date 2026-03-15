import { useEffect, useState } from 'react'



function App() {
  const [count, setCount] = useState(0)

  const [tasks, setTasks] = useState([]);

  const [task, setTask] = useState({
    name:"",
    content: "",
    id: 0
  })

  const updateList = async ()=>{
    const request = await fetch("http://10.0.0.139:8080/api/task", {
      method:"post", 
      body: JSON.stringify(task),
      headers: {
        "Content-Type": "application/json"
      }
    })
console.log(request)
    const response = await request.json()
    setTasks((prevTasks) => {
      return [...prevTasks, task];
    });
  }

  useEffect(()=>{
    const fetchTasks = async ()=>{
      const request = await fetch('http://10.0.0.139:8080/api/task');
      if (request == null || !request.ok ) return;
      const response = await request.json()

      
      setTasks(response)
    }

    fetchTasks()
  }, [])
  return (
  <>
    <ul style={{listStyleType:'none'}}>
      {
        tasks.length > 0 ?
          tasks.map(item => (
            <li key={item.id}>

            <input  value={item.name}/>
            </li>
          ))
        : "nothing"
      }
      <li>
        <input onChange={(e)=>{
          setTask({name:e.target.value})
        }} value={task.name} placeholder='New Note' />
        <button onClick={()=>{
          updateList()
        }}>Submit</button>
      </li>
    </ul>
  </>
)

}

export default App
