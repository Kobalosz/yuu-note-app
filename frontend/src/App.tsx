import { useEffect, useState } from 'react'

interface Task {
  id?: number,
  name:string,
  content:string
}

function App() {

  const [tasks, setTasks] = useState<Task[]>([]);

  const [task, setTask] = useState<Task>({
    name:"",
    content: "",
    
  })

  const updateList = async ()=>{
    await fetch("http://localhost:8080/api/task", {
      method:"post", 
      body: JSON.stringify(task),
      headers: {
        "Content-Type": "application/json"
      }
    })

    setTasks((prevTasks) => {
      return [...prevTasks, task];
    });
    setTask({name:"",content:"", id:0})
  }

  useEffect(()=>{
    const fetchTasks = async ()=>{
      const request = await fetch('http://localhost:8080/api/task');
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
            <li key={item?.id }>

            <input  value={item.name}/>
            <button onClick={()=>{

              setTasks((prevTasks) => {
                return prevTasks.filter((t) => t.id !== item.id);
              });
            }}>Delete</button>
            </li>
          ))
        : "nothing"
      }
      <li style={{display:'flex', flexDirection:"column", justifyItems:"center", alignItems:"center", marginTop:"10px"}}>
        <input onChange={(e)=>{
          setTask({...task, name:e.target.value})
        }} value={task.name} placeholder='New Note' />
        <textarea value={task.content} onChange={(e)=>{
          setTask({...task,content:e.target.value})
        }}/>

        
        <button onClick={()=>{
          updateList()
        }}>Submit</button>
      </li>
    </ul>
  </>
)

}

export default App
