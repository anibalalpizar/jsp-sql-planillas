<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <script src="https://cdn.tailwindcss.com"></script>

        <title>Document</title>
    </head>

    <body>
        <section
            class="bg-gray-50 min-h-screen flex items-center justify-center items-center"
            >
            <!-- Login container -->
            <div
                class="bg-gray-100 flex rounded-2xl shadow-lg max-w-3xl p-5 items-center"
                >
                <!-- Login form -->
                <div class="md:w-1/2 px-16">
                    <h2 class="font-bold text-2xl text-[#002D74]">Login</h2>
                    <p class="text-sm mt-4 text-[#002D74]">
                        El mejor sistema de planillas del mercado
                    </p>

                    <form action="srvUsuario?accion=verificar" method="POST" class="flex flex-col gap-4">
                        <input
                            class="p-2 mt-8 rounded-xl border"
                            type="text"
                            id="txtUsu"
                            name="txtUsu"
                            placeholder="Email"
                            />
                        <div class="relative">
                            <input
                                class="p-2 rounded-xl border w-full"
                                type="password"
                                name="txtPass"
                                id="txtPass"
                                placeholder="password"
                                />
                        </div>
                        <button
                            class="bg-[#002D74] rounded-xl text-white py-2 hover:scale-105 duration-300"
                            >
                            Login
                        </button>
                    </form>
                    <div class="mt-10 grid grid-cols-3 items-center text-gray-500">
                        <hr class="border-gray-400" />
                        <p class="text-center text-sm">O</p>
                        <hr class="border-gray-400" />
                    </div>
                    <form action="registro.xhtml">
                        <div class="mt-3 text-xs flex justify-between items-center">
                            <p>¿No tienes una cuenta?</p>
                            <button
                                class="py-2 px-5 bg-white border rounded-xl hover:scale-110 duration-300"
                                >
                                Registrarte
                            </button>
                        </div>
                    </form>
                </div>

                <!-- Image form -->
                <div class="sm:block hidden w-1/2">
                    <img
                        class="rounded-2xl"
                        src="https://cdn.esbrillante.mx/wp-content/uploads/2017/06/Previo_Tarjetas.jpg"
                        alt="login image"
                        />
                </div>
            </div>
        </section>
    </body>
</html>
