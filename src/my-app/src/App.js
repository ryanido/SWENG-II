import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import Grid from '@mui/system/Unstable_Grid';
import './App.css';

function App() {
  const [value, setValue] = React.useState("");
  const [result, setResult] = React.useState("");
  const [helper, setHelper] = React.useState("");
  const [error, setError] = React.useState("false");

  function startCalc(){
    if(value==""){
      setError("true")
      setHelper("ERROR: The input must not be empty")
    }
    else{
      console.log(value);
      setResult("")
      setHelper("")
      setError("false")
      fetch('/api', {
        method: 'POST',
        body: JSON.stringify({ input:  value }),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json'
        },
      }).then(response => response.json())
      .then(data => {
        if(data.error == "true"){
          setError("true")
          setHelper(data.result)
        }
        else{
          setResult(data.result)
        }
      })
    }
  }

  return (
    <div className="App">
      <header className="App-header">
        <p className="App-title">
          Calculator Web Application
        </p>
        <div className="App-welcome">
          Welcome to the calculator web app.
        </div>
      </header>
      <body>
        <Box
          component="form"
          sx={{textAlign: "center", background: "#282c34"}}
          noValidate
          autoComplete="off"
        >
          <Grid container spacing={0}>
            <Grid 
              xs={12}
              sx={{
                '& .MuiTextField-root': { mt: 12, width: '100vh' },
                '& .MuiOutlinedInput-root': {
                  '& fieldset': {
                    borderColor: "#64748B",
                  },
                  '&:hover fieldset': {
                    borderColor: "#cfd8dc",
                  },
                  '&.Mui-focused fieldset': {
                    borderColor: '#0288d1',
                  },
                },
              }}
            >
              <TextField
                error= {error == "true"}
                id="inputbox"
                sx={{input: { color: '#e1f5fe' }}}
                placeholder="Enter a valid expression" 
                value={value}
                helperText={helper}
                onChange={(newValue) => {
                  setValue(newValue.target.value);
                }}
                onKeyPress={(ent) => {
                  if (ent.key === "Enter") {
                    startCalc();
                  }
                }}
              />
            </Grid>
            <Grid xs={12} sx={{alignItems:"center"}}>
              <Stack 
                  spacing={10} 
                  direction="row"
                  sx={{m:"auto", mt:5, width: "100vh", justifyContent: "center"}}
              >
                <Button 
                  variant="contained"
                  onClick={(cclk) => {
                    startCalc();
                  }}
                >
                  Calculate
                </Button>
                <Button 
                  variant="outlined"
                  onClick={() => {
                    setValue("");
                    setResult("");
                    setHelper("");
                    setError("false")
                  }}
                >
                  Reset
                </Button>
              </Stack>
            </Grid>
            <Grid 
              xs={12}
              sx={{'& .MuiTextField-root': { mt: 5, width: '100vh' },
              '& .MuiOutlinedInput-root': {
                '& fieldset': {
                  borderColor: "#64748B",
                },
                '&:hover fieldset': {
                  borderColor: "#cfd8dc",
                },
                '&.Mui-focused fieldset': {
                  borderColor: '#0288d1',
                },
              },}}
              >
              <TextField
                id="outputbox"
                sx={{input: { color: '#e1f5fe' }}}
                placeholder="Result goes here"
                value={result}
                InputProps={{
                  readOnly: true,
                }}
              />
            </Grid>
          </Grid>
        </Box>
        </body>
      <footer className='App-footer'></footer>
    </div>
  );
}

export default App;
