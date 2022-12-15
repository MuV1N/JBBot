# JBBot
This Bot is for the community [Discord](https://discord.gg/pDausEpMR5)
of [TheRealJ0sh](https://twitch.tv/therealj0sh) and [BlueEye01](https://twitch.tv/blueeye01).
The bot is currently under reworking in case of this I created this repo! You can create pull requests 
if you want to help me and the dev team of the Bot to improve the code.

## Checklist
- [ ] Social Commands
- [x] Join and Quit messages
- [ ] Ticket System
- [x] Twitch Notifications
- [ ] YouTube notifications
- [ ] Server Manage Commands
- [ ] Private Channels
- [ ] Translate in other languages

### Developing
If you want to code your self you need to create a class named Token in util

```Java
package de.muv1n.jbbot;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Dotenv dotenv = Dotenv.load();

        new JBBot(dotenv.get("TEST_TOKEN"));
    }
}
```

```dotenv
TOKEN=YOUR_TOKEN_HERE
```


 
