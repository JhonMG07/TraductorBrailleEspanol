import sys
import pyttsx3

args = sys.argv[1:]

engine = pyttsx3.init()
engine.setProperty('rate', 150)  # Velocidad de la voz
engine.setProperty('volume', 1.0) # Volumen al máximo (valor entre 0 y 1)

    # Encuentra y configura la voz en español
voices = engine.getProperty('voices')
for voice in voices:
    if 'Microsoft Sabina Desktop - Spanish (Mexico)' in voice.name or 'Spanish' in voice.name:
        engine.setProperty('voice', voice.id)
        break

for t in args:
    engine.say(t)
    
engine.runAndWait()