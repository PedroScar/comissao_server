package di

import model.colaborador.ColaboradorRepository
import model.funcionario.FuncionarioRepository
import model.loja.LojaRepository
import org.koin.dsl.module

fun ComissaoDI() = module {
    single<FuncionarioRepository> { model.funcionario.FuncionarioRepositoryPostgres() }
    single<ColaboradorRepository> { model.colaborador.ColaboradorRepositoryPostgres() }
    single<LojaRepository> { model.loja.LojaRepositoryPostgres() }
}