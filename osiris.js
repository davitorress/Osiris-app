const fs = require("fs");
const xml2js = require("xml2js");
const os = require("os");

function findIPAddress() {
  let ipAddress = null;
  const networkInterfaces = os.networkInterfaces();
  const interfaceNames = Object.keys(networkInterfaces);

  for (const name of interfaceNames) {
    const interfaces = networkInterfaces[name];
    for (const iface of interfaces) {
      if (iface.family === "IPv4" && !iface.internal) {
        ipAddress = iface.address;
        break;
      }
    }
    if (ipAddress) {
      break;
    }
  }

  return ipAddress;
}

const ipAddress = findIPAddress();

if (ipAddress) {
  console.log("Endereço IP encontrado:", ipAddress);

  const xmlObj = {
    "network-security-config": {
      "domain-config": {
        $: {
          cleartextTrafficPermitted: "true",
        },
        domain: [
          { $: { includeSubdomains: "true" }, _: "127.0.0.1" },
          { $: { includeSubdomains: "true" }, _: "localhost" },
          { $: { includeSubdomains: "true" }, _: "res.cloudinary.com" },
          { $: { includeSubdomains: "true" }, _: ipAddress },
        ],
      },
    },
  };

  const builder = new xml2js.Builder();
  const xml = builder.buildObject(xmlObj);

  // Troque para o seu path
  const filePath =
  "app/src/main/res/xml/network_security_config.xml";

  const filePathEnv =
    "app/src/main/assets/env";

  console.log(filePath);

  console.log(`
     _____       _        _      
    |  _  |     (_)      (_)     
    | | | | ___  _  _ __  _  ___ 
    | | | |/ __|| || '__|| |/ __|
    \\ \\_/ /\\__ \\| || |   | |\\__ \\
     \\___/ |___/|_||_|   |_||___/
                                   
    `);
  console.log("Configuração de IP para o aplicativo Osiris");
  console.log("\n");
  console.log("################################");
  console.log("\n");

  if (fs.existsSync(filePath)) {
    fs.unlinkSync(filePath);
    console.log(`Arquivo ${filePath} anterior deletado.`);
  }

  fs.writeFile(filePathEnv, ipAddress, (err) => {
    if(err) {
      console.error(err);
      return;
    }
    console.log(`Arquivo da Env em ${filePath} gerado com sucesso.`);
  });

  fs.writeFile(filePath, xml, (err) => {
    if (err) {
      console.error(err);
      return;
    }
    console.log(`Arquivo ${filePath} gerado com sucesso.`);
  });
} else {
  console.log(
    "Nenhum endereço IP disponível. Não foi possível gerar o arquivo XML."
  );
}
