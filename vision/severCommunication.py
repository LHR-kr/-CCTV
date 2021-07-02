import asyncio
import websockets

async def connect():
    async with websockets.connect("주소") as websocket:
        await websocket.send("보낼 데이터")
        data=await websocket.rev() # 서버에서 데이터 받기
asyncio.get_event_loop().run_until_complete(connect())